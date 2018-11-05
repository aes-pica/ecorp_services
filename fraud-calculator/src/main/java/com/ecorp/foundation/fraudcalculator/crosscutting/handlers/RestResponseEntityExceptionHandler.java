package com.ecorp.foundation.fraudcalculator.crosscutting.handlers;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.ecorp.foundation.fraudcalculator.crosscutting.exceptions.FraudsApplicationException;
import com.ecorp.foundation.fraudcalculator.model.FraudCalculationResponse;

/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
 * Exception handler
 *
 * @author Gustavo Adolfo Arciniegas Plazas
 * @version 1.0
 * @since 2018-11-04
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(value = { FraudsApplicationException.class })
	protected ResponseEntity<Object> handleConflict(Exception ex, WebRequest request) {
		
		FraudCalculationResponse response = FraudCalculationResponse
				.builder()
				.diagnostic(ex.getMessage())
				.expectedIPAdddress("N/A")
				.build();
		
		return handleExceptionInternal(ex, response, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}
}
