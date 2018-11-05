package com.ecorp.foundation.fraudcalculator.apis.rest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ecorp.foundation.fraudcalculator.crosscutting.exceptions.FraudsApplicationException;
import com.ecorp.foundation.fraudcalculator.model.FraudCalculationRequest;
import com.ecorp.foundation.fraudcalculator.model.FraudCalculationResponse;
import com.ecorp.foundation.fraudcalculator.usecase.CreditCardFraudsCalculator;

/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Exposes CreditCardFraudsCalculator as a remote service through a rest api 
 *
 * @author Gustavo Adolfo Arciniegas Plazas
 * @version 1.0
 * @since 2018-11-04
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

@RestController
@RequestMapping(value = "/credit-cards-fraud-api", produces = MediaType.APPLICATION_JSON_VALUE)
public class CreditCardFraudsApi {

	private static final Logger LOGGER = LogManager.getLogger(CreditCardFraudsApi.class);

	@Autowired
	private CreditCardFraudsCalculator fraudCalculator;

	/**
	 * This method returns the total number of fake credit cards created for a given day,
	 * this response includes as well an expected IP address involved in fraud generation.
	 * This api is exposed as a http get resource 
	 *
	 * @param expectedDay  an absolute URL giving the base location of the image
	 * @return a proper data structure with the total number of fake credit cards and expected ip address
	 */

	@RequestMapping(value = "/calculate-fake-cards/{expectedDay}", method = RequestMethod.GET)
	public FraudCalculationResponse calculate(final @PathVariable int expectedDay)
			throws FraudsApplicationException {

		LOGGER.info("receiving request for fake credit card calculations for day: {}", expectedDay);

		return fraudCalculator.calculate(FraudCalculationRequest.builder().dayNumber(expectedDay).build());
	}

}
