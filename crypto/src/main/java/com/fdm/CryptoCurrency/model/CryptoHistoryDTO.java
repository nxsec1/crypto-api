package com.fdm.CryptoCurrency.model;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CryptoHistoryDTO {
	// COMMENT: use model instead of map
	private MarketData market_data;
}
