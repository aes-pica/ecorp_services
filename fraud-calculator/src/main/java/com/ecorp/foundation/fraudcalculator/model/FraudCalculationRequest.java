package com.ecorp.foundation.fraudcalculator.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FraudCalculationRequest {

	private int dayNumber;
	
}
