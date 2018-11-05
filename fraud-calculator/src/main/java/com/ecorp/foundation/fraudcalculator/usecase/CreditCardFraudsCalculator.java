package com.ecorp.foundation.fraudcalculator.usecase;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.ecorp.foundation.fraudcalculator.crosscutting.exceptions.FraudsApplicationException;
import com.ecorp.foundation.fraudcalculator.model.FraudCalculationRequest;
import com.ecorp.foundation.fraudcalculator.model.FraudCalculationResponse;

/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
 * Provides an algorithm for determining how many credit cards will be 
 * created for a given day
 *
 * @author Gustavo Adolfo Arciniegas Plazas
 * @version 1.0
 * @since 2018-11-04
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

@Service
public class CreditCardFraudsCalculator {

	private static final String TEMPLATE_MESSAGE = "%d credit cards will be created for day number %d from ip %s";
	private static final int BASE_IP_NUMBER = 200;
	private static final String IP_ADDRES_FORMAT = "181.75.48.%s";
	private static final int DEFAULT_NEXT_TERM = 1;
	private static final int DEFAULT_INDEX = 1;
	private static final Logger LOGGER = LogManager.getLogger(CreditCardFraudsCalculator.class);

	
	/**
	 * This method returns the total number of fake credit cards created for a given day,
	 * this reponses includes as well an expected IP address involved in fraud generation.
	 *
	 * @param request an object with the reference day involved in calculations
	 * @return a proper data structure with the total number of fake credit cards and expected ip address
	 */
	
	public FraudCalculationResponse calculate(final FraudCalculationRequest request) throws FraudsApplicationException {

		LOGGER.info("receiving request object for fake credit card calculations with input: {}", request);

		if( request.getDayNumber() == 0 )
			throw new FraudsApplicationException("An invalid day has been provided: " + request.getDayNumber());
		
		String ip = identifyIP(request.getDayNumber());
		int totalCreditCards = calculateRecursively(DEFAULT_INDEX, request.getDayNumber(), DEFAULT_NEXT_TERM);
		
		return FraudCalculationResponse.builder()
				.totalExpectedCreditCards(totalCreditCards)
				.expectedIPAdddress(ip)
				.diagnostic(generateDiagnosticMessage(totalCreditCards, request.getDayNumber(), ip))
				.build();

	}
	
	
	/**
	 * Calculates recursively the total number of fake credit cards created for a given day
	 *
	 * @param index a counter for determining the total of iterations
	 * @param expectedDay the day for determining how many credit cards have been created
	 * @param nextTerm a parameter loaded recursively as part of the formula
	 * @return a proper data structure with the total number of fake credit cards and expected ip address
	 */

	public int calculateRecursively(final int index, final int expectedDay, int nextTerm) {

		if (index < expectedDay) {
			nextTerm = index * (nextTerm);
			return calculateRecursively(index + 1, expectedDay, nextTerm);
		} else {
			return (index * nextTerm) * expectedDay;
		}
	}

	
	/**
	 *  Generates the ip identified as possible source of the credit cards generation
	 *
	 * @param expectedDay the day for determining how many credit cards have been created
	 * @return an ip address
	 */
	
	public String identifyIP( final int expectedDay ) {
		return String.format(IP_ADDRES_FORMAT,String.valueOf(BASE_IP_NUMBER + expectedDay));
	}
	
	
	/**
	 * Generates the diagnostic message to be propagated in the response
	 *
	 * @param totalCreditCards the total of credit cards calculated
	 * @param expectedDay the day for determining how many credit cards have been created
	 * @param ip the ip identified as possible source of the credit cards generation
	 * @return a message with all of the parameters
	 */
	
	public String generateDiagnosticMessage( final int totalCreditCards, final int expectedDay, String ip ) {
		return String.format(TEMPLATE_MESSAGE, totalCreditCards, expectedDay, ip );
	}

}
