package com.fdm.CryptoCurrency.model;

import java.util.Map;

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
	private Map<String, Object> market_data;
}
