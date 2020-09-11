package com.fdm.CryptoCurrency.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CryptoCurrencyDetail {
	private String id;
	private String symbol;
	private String name;
	private String genesis_date;
	private String last_update;
	private CurrencyPrice market_cap;
	private CurrencyPrice current_price;
	private CurrencyPrice price_percentage_change_in_24hr;
	private CurrencyPrice lastWeek_price;

}
