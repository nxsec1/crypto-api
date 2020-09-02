package com.fdm.CryptoCurrency.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdm.CryptoCurrency.api.CurrencyDetail;
import com.fdm.CryptoCurrency.client.CryptoDetailDTO;
import com.fdm.CryptoCurrency.client.CryptoFeignClient;
import com.fdm.CryptoCurrency.client.CryptoHistoryDTO;

@Service
public class ClientService {

	private CryptoFeignClient client;
	
	@Autowired
	public ClientService(CryptoFeignClient client) {
		this.client = client;
	}

	public CurrencyDetail getCurrencyDetail(String id) {
		CurrencyDetail cd = new CurrencyDetail();
		CryptoDetailDTO dto = client.findCurrency(id);
		cd.setId(dto.getId());
		cd.setSymbol(dto.getSymbol());
		cd.setName(dto.getName());
		cd.setGenesis_date(dto.getGenesis_date());
		cd.setLast_update(dto.getLast_updated());
		
		Map<String,Object> data = dto.getMarket_data();
		JSONObject market_caps = (JSONObject) data.get("market_cap");
		String market_cap = Long.toString(market_caps.getLong("usd"));
		cd.setMarket_cap(market_cap);
		
		JSONObject rates = (JSONObject) data.get("current_price");
		HashMap<String, String> current_price = getPrice(rates);
		cd.setCurrent_price(current_price);
		
		JSONObject changes = (JSONObject) data.get("price_change_percentage_24h_in_currency");
		HashMap<String, String> price_change = getPriceChange(changes);
		cd.setPrice_percentage_change_in_24hr(price_change);
		
		DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		LocalDateTime now = LocalDateTime.now();
		LocalDateTime then = now.minusDays(7);
		String date = String.format(then.format(format));
		
//		CryptoHistoryDTO historyDto = client.findHistory(id,date);
		return cd;
	}
	
	public HashMap<String, String> getPrice(JSONObject obj) {
		List<String> currencies = new ArrayList<>(Arrays.asList("jpy", "aud", "usd"));
		HashMap<String, String> price = new HashMap<String, String>();
		for (String currency : obj.keySet()) {
			if (currencies.contains(currency)) {
				Double p = obj.getDouble(currency);
				price.put(currency, String.format("%.2f", p));
			}
		}
		return price;
	}
	
	public HashMap<String, String> getPriceChange(JSONObject obj) {
		List<String> currencies = new ArrayList<>(Arrays.asList("jpy", "aud", "usd"));
		HashMap<String, String> price = new HashMap<String, String>();
		for (String currency : obj.keySet()) {
			if (currencies.contains(currency)) {
				price.put(currency, Double.toString(obj.getDouble(currency)));
			}
		}
		return price;
	}
	

}
