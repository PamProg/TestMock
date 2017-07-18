package dev.service;

import static org.assertj.core.api.Assertions.*;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dev.exception.CalculException;

public class CalculServiceTest {

	private static final Logger LOG = LoggerFactory.getLogger(CalculServiceTest.class);

	@Test
	public void testAdditionner() throws Exception {

		LOG.info("Etant donnée une instance de la classe CalculService");
		CalculService calcul = new CalculService();

		LOG.info("Lorsque j'évalue l'addition de l'expression 1+3+4");
		int somme = calcul.additionner("1+3+4");
		
		LOG.info("Alors j'obtiens le résultat {}", somme);
		assertThat(somme).isEqualTo(8);
	}
	
	@Test(expected = CalculException.class)
	public void testAdditionnerExpressionNonValide() {
		
		LOG.info("Etant donnée une instance de la classe CalculService");
		CalculService calcul = new CalculService();

//		LOG.info("Lorsque j'évalue l'addition de l'expression 1+3+4");
		int somme = calcul.additionner("1+3+4dfdf");
	}
}
