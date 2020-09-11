package com.fdm.CryptoCurrency.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CryptoCurrency {
	private String id;
	private String current_price;
	private String market_cap;
	private StatusUpdateDTO statusUpdates;
}
