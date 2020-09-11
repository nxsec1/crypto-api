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
	private CurrencyPriceDTO market_cap;
	private CurrencyPriceDTO current_price;
	private CurrencyPriceDTO price_percentage_change_in_24hr;
	private CurrencyPriceDTO lastWeek_price;

}
