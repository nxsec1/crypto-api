package com.fdm.CryptoCurrency.model;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CryptoCurrency {
	private String id;
	private String current_price;
	private String market_cap;
	private List<StatusUpdate> statusUpdates;
}
