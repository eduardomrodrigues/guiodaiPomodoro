package com.guiodai.dominio.guiodai.services;

import org.junit.Test;

public class GuiodaiServicesTest {


	@Test
	public void testRecuperarPomodoroDaIssue(){
		
		GuiodaiServices g = new GuiodaiServices();
		org.junit.Assert.assertNotNull(g.recuperarPomodoro(1L, 1L).getPomodoros());
		
	}
	
	@Test
	public void testIncrementarPomodoroDaIssue(){
		
		GuiodaiServices g = new GuiodaiServices();
		int before = g.recuperarPomodoro(1L, 1L).getPomodoros();
		
		g.incrementarPomodoro(1L, 1L);
		
		int after = g.recuperarPomodoro(1L, 1L).getPomodoros();
		
		org.junit.Assert.assertTrue(after == (before + 1));
		
	}



}
