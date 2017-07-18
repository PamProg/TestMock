package dev.console;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.contrib.java.lang.system.TextFromStandardInputStream.emptyStandardInputStream;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Scanner;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemOutRule;
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dev.exception.CalculException;
import dev.service.CalculService;


public class AppTest {

	private static final Logger LOG = LoggerFactory.getLogger(AppTest.class);

	@Rule public final SystemOutRule systemOutRule = new SystemOutRule().enableLog();
	private App app;
	private CalculService calculService;
	@Rule private final TextFromStandardInputStream systemInMock = emptyStandardInputStream();
	

	@Before
	public void setUp() throws Exception {
		Scanner sc = new Scanner(System.in);
		this.calculService = mock(CalculService.class);
		this.app = new App(sc, calculService);
	}

	@Test
	public void testAfficherTitre() throws Exception {
		this.app.afficherTitre();
		String logConsole = systemOutRule.getLog();
		assertThat(logConsole).contains("**** Application Calculatrice ****");
	}

	@Test
	public void testEvaluer() throws Exception {
		LOG.info("Etant donné, un service CalculService qui retourne 35 à l'évaluation de l'expression 1+34");
		String expression = "1+34";
		when(calculService.additionner(expression)).thenReturn(35);
		
		LOG.info("Lorsque la méthode evaluer est invoquée");
		this.app.evaluer(expression);
		
		LOG.info("Alors le service est invoqué avec l'expression {}", expression);
		verify(calculService).additionner(expression);
		
		LOG.info("Alors dans la console, s'affiche 1+34=35");
		assertThat(systemOutRule.getLog()).contains("1+34=35");
	}
	
	@Test
	public void testEvaluerInvalide() throws Exception {
		LOG.info("Etant donné, un service CalculService qui retourne une CalculException à l'évaluation de l'expression abc");
		String expression = "abc";
		when(calculService.additionner(expression)).thenThrow(new CalculException());
		
		LOG.info("Lorsque la méthode evaluer est invoquée");
		this.app.evaluer(expression);
		
		LOG.info("Alors le service est invoqué avec l'expression {}", expression);
		verify(calculService).additionner(expression);
		
		assertThat(systemOutRule.getLog()).contains("L'expression " + expression + " est invalide");
	}
	
	@Test
	public void testDemarrerFin() throws Exception {
		LOG.info("Etant donné l'utilisateur inscrivant la chaine 'fin");
		systemInMock.provideLines("fin");
		
		LOG.info("Lorsque la méthode demarrer est invoquée");
		this.app.demarrer();
		
		assertThat(systemOutRule.getLog()).contains("Aurevoir :-(");
		
	}
}