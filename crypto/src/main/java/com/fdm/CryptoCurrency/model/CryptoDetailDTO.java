package com.fdm.CryptoCurrency.model;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CryptoDetailDTO {
	private String id;
	private String symbol;
	private String name;
	private String genesis_date;
	private String last_updated;
	private MarketData market_data;
}
