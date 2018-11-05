package com.ecorp.foundation.fraudcalculator.usecase;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.ecorp.foundation.fraudcalculator.crosscutting.exceptions.FraudsApplicationException;
import com.ecorp.foundation.fraudcalculator.model.FraudCalculationRequest;
import com.ecorp.foundation.fraudcalculator.model.FraudCalculationResponse;

public class CreditCardFraudsCalculatorTest {

	private CreditCardFraudsCalculator fraudCalculator;

	@Before
	public void init() {
		fraudCalculator = new CreditCardFraudsCalculator();
	}

	@Test
	public void calculationsTestForDay4() throws FraudsApplicationException {

		FraudCalculationRequest request = FraudCalculationRequest.builder().dayNumber(4).build();

		FraudCalculationResponse response = fraudCalculator.calculate(request);

		assertEquals(response.getTotalExpectedCreditCards(),96);
		assertEquals(response.getExpectedIPAdddress(),"181.75.48.204");
		assertEquals(response.getDiagnostic(),"96 credit cards will be created for day number 4 from ip 181.75.48.204");

	}
	
	@Test
	public void calculationsTestForDay6() throws FraudsApplicationException {

		FraudCalculationRequest request = FraudCalculationRequest.builder().dayNumber(6).build();

		FraudCalculationResponse response = fraudCalculator.calculate(request);

		assertEquals(response.getTotalExpectedCreditCards(),4320);
		assertEquals(response.getExpectedIPAdddress(),"181.75.48.206");
		assertEquals(response.getDiagnostic(),"4320 credit cards will be created for day number 6 from ip 181.75.48.206");

	}
	
	@Test(expected=FraudsApplicationException.class)
	public void calculationsTestForDay0WithException() throws FraudsApplicationException {

		FraudCalculationRequest request = FraudCalculationRequest.builder().dayNumber(0).build();
		fraudCalculator.calculate(request);
	}

}
