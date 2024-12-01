package co.aurasphere.gomorrasql.model;

import java.util.List;

/**
 * Exception thrown by GomorraSQL when an error occurs during query
 * parsing/execution.
 * 
 * @author Donato Rimenti
 *
 */
public class MannaggGiudException extends RuntimeException {

	public MannaggGiudException(List<String> expectedTokens, String actualToken) {
		super("Expected one of " + expectedTokens + " but got [" + actualToken + "]");
	}

	public MannaggGiudException(String expectedToken, String token) {
		super("Expected [" + expectedToken + "] but got [" + token + "]");
	}

	public MannaggGiudException(String message) {
		super(message);
	}

	public MannaggGiudException(Exception e) {
		super(e);
	}

}
