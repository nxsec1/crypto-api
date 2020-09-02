package com.fdm.CryptoCurrency.client;

import java.util.Map;

import org.json.JSONObject;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CryptoDetailDTO {
	private String id;
	private String symbol;
	private String name;
	private String genesis_date;
	private String last_updated;
	private JSONObject market_data;
}
