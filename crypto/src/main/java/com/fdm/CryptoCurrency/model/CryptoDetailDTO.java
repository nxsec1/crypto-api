package com.fdm.CryptoCurrency.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CryptoDetailDTO {
	private String id;
	private String symbol;
	private String name;
	private String genesis_date;
	private String last_updated;
	private MarketDataDTO market_data;
}
