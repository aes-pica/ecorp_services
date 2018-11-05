package com.ecorp.foundation.fraudcalculator.crosscutting.exceptions;

/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
 * Exception definition
 *
 * @author Gustavo Adolfo Arciniegas Plazas
 * @version 1.0
 * @since 2018-11-04
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

public class FraudsApplicationException extends Exception {

	private static final long serialVersionUID = 1L;

	public FraudsApplicationException(String message) {
		super(message);
	}

}
