package com.fdm.CryptoCurrency.client;

import java.util.ArrayList;
import java.util.HashMap;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MarketCryptoDTO {
	private String id;
	private String currentPrice;
	private String marketCap;
	private ArrayList<HashMap<String, String>> statusUpdates;
}
