package com.guiodai.dominio.github.dominio;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.AuthCache;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.BasicAuthCache;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.h2.util.StringUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import com.guiodai.dominio.github.exceptions.BadRequestException;
import com.guiodai.dominio.github.exceptions.UnauthorizedException;

public abstract class GitHubHttpConnection {

	protected CloseableHttpClient httpclient;

	protected HttpHost target;

	protected HttpClientContext localContext;

	private void autenticarGitHub(String usuario, String senha) {

		target = new HttpHost("api.github.com", 443, "https");

		CredentialsProvider credsProvider = new BasicCredentialsProvider();
		credsProvider.setCredentials(
				new AuthScope(target.getHostName(), target.getPort()),
				new UsernamePasswordCredentials(usuario, senha));
		httpclient = HttpClients.custom()
				.setDefaultCredentialsProvider(credsProvider).build();

		AuthCache authCache = new BasicAuthCache();
		BasicScheme basicAuth = new BasicScheme();
		authCache.put(target, basicAuth);

		localContext = HttpClientContext.create();
		localContext.setAuthCache(authCache);

	}

	protected List<JSONObject> gitGet(String url, String usuario, String senha) throws UnauthorizedException, BadRequestException {

		this.autenticarGitHub(usuario, senha);

		List<JSONObject> retorno = new ArrayList<JSONObject>();

		try {

			HttpGet httpget = new HttpGet(url);

			CloseableHttpResponse response = httpclient.execute(target,
					httpget, localContext);
			try {
				int status = response.getStatusLine().getStatusCode();
				
				if(401 == status){
					throw new UnauthorizedException();
				}
				if(400 == status){
					throw new BadRequestException();
				}
				
				String returnJson = EntityUtils.toString(response.getEntity());
				if(200 == status && StringUtils.isNullOrEmpty(returnJson)){
					return new ArrayList<JSONObject>();
				}
				
				
				
				if('[' == returnJson.charAt(0)){
					JSONArray jsonArray = new JSONArray(returnJson);
					for (int i = 0; i < jsonArray.length(); i++) {
						JSONObject o = jsonArray.getJSONObject(i);
						retorno.add(o);
					}
				}else{
					
					JSONObject jsonObject = new JSONObject(returnJson);
					retorno.add(jsonObject);

				}
			} finally {
				response.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				httpclient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return retorno;
	}

}
