package com.ecorp.foundation.fraudcalculator.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FraudCalculationResponse {

	private int totalExpectedCreditCards;
	private String expectedIPAdddress;
	private String diagnostic;
	
}
