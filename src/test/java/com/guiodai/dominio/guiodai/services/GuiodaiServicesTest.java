package com.guiodai.dominio.guiodai.services;

import org.junit.Test;

public class GuiodaiServicesTest {


	@Test
	public void testRecuperarPomodoroDaIssue() throws PomodoroNotFoundException{
		
		GuiodaiServices g = new GuiodaiServices();
		org.junit.Assert.assertNotNull(g.recuperarPomodoro(1L, 1L).getPomodoros());
		
	}
	
	@Test(expected = PomodoroNotFoundException.class)
	public void testRecuperarPomodoroDaIssueQueNaoExiste() throws PomodoroNotFoundException{
		
		GuiodaiServices g = new GuiodaiServices();
		org.junit.Assert.assertNotNull(g.recuperarPomodoro(1213L, 1123L).getPomodoros());
		
	}
	
	@Test
	public void testIncrementarPomodoroDaIssue() throws PomodoroNotFoundException{
		
		GuiodaiServices g = new GuiodaiServices();
		int before = g.recuperarPomodoro(1L, 1L).getPomodoros();
		
		g.incrementarPomodoro(1L, 1L);
		
		int after = g.recuperarPomodoro(1L, 1L).getPomodoros();
		
		org.junit.Assert.assertTrue(after == (before + 1));
		
	}
	
	@Test
	public void testIncrementarPomodoroDaIssueQueNaoExiste() throws PomodoroNotFoundException{
		
		GuiodaiServices g = new GuiodaiServices();
		g.incrementarPomodoro(123121L, 123121L);
		org.junit.Assert.assertNotNull(g.recuperarPomodoro(123121L, 123121L));
		
		
	}



}
