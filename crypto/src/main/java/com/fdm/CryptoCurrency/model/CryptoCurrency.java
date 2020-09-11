package com.fdm.CryptoCurrency.model;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class CryptoCurrency {
	private String id;
	private String current_price;
	private String market_cap;
	private List<StatusUpdate> statusUpdates;
}
