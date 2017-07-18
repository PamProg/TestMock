package dev.service;

import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dev.exception.CalculException;

public class CalculService {

	private static final Logger LOG = LoggerFactory.getLogger(CalculService.class);
	
	public int additionner(String expression) throws CalculException {
		
		LOG.debug("Evaluation de l'expression {}", expression);
		
		Stream<String> stream;
		int somme;
		
		try {
			stream = Stream.of(expression.split("\\+"));
			somme = stream.mapToInt(x -> Integer.parseInt(x)).sum();
		} catch (Exception e) {
			LOG.debug("L'expression {} est invalide", expression);
			throw new CalculException("Erreur d'expression : expression invalide", e);
		}
		
		return somme;
	}
}
