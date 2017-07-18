package dev.console;

import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dev.exception.CalculException;
import dev.service.CalculService;

public class App {

	private static final Logger LOG = LoggerFactory.getLogger(App.class);
	
	private Scanner scanner;
	private CalculService calculatrice;
	public App(Scanner scanner, CalculService calculatrice) {
		this.scanner = scanner;
		this.calculatrice = calculatrice;
	}

	protected void afficherTitre() {
		LOG.info("**** Application Calculatrice ****");
	}

	public void demarrer() {
		afficherTitre();
		LOG.info("Veuillez entrer une expression");
		String input = scanner.next();
		
		if("fin".equals(input)) {
			LOG.info("Aurevoir :-(");
		} else {
			evaluer(input);
		}
		
	}

//	public void demarrer() {
//		afficherTitre();
//		LOG.info("Veuillez entrer une expression");
//		
//		String input; 
//		
//		do {
//			input = scanner.next();
//			
//			evaluer(input);
//			
//		} while (!"fin".equals(input));
//		
//		LOG.info("Aurevoir :-(");
//		
//	}
	
	protected void evaluer(String expression) {
		
		int somme = 0;
		try {
			somme = calculatrice.additionner(expression);
		} catch (CalculException e) {
			LOG.info("L'expression {} est invalide", expression);
		}
		
		LOG.info("{}={}", expression, somme);
	}
}
