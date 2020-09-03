package com.fdm.CryptoCurrency.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdm.CryptoCurrency.client.CryptoFeignClient;
import com.fdm.CryptoCurrency.model.CryptoCurrency;
import com.fdm.CryptoCurrency.model.CryptoDetailDTO;
import com.fdm.CryptoCurrency.model.CryptoHistoryDTO;
import com.fdm.CryptoCurrency.model.CurrencyDetail;
import com.fdm.CryptoCurrency.model.StatusUpdate;

@Service
public class ClientService {

	private CryptoFeignClient client;

	@Autowired
	public ClientService(CryptoFeignClient client) {
		this.client = client;
	}

	@SuppressWarnings("unchecked")
	public CurrencyDetail getCurrencyDetail(String id) {
		CurrencyDetail cd = new CurrencyDetail();
		CryptoDetailDTO dto = client.findCurrency(id);
		cd.setId(dto.getId());
		cd.setSymbol(dto.getSymbol());
		cd.setName(dto.getName());
		cd.setGenesis_date(formatDate(dto.getGenesis_date()));
		cd.setLast_update(formatDate(dto.getLast_updated().substring(0, 10)));

		Map<String, Object> data = dto.getMarket_data();
		HashMap<String, Long> market_caps = (HashMap<String, Long>) data.get("market_cap");
		String market_cap = Long.toString(market_caps.get("usd"));
		cd.setMarket_cap(market_cap);

		HashMap<String, Object> rates = (HashMap<String, Object>) data.get("current_price");
		HashMap<String, String> current_price = getPrice(rates);
		cd.setCurrent_price(current_price);

		HashMap<String, Object> changes = (HashMap<String, Object>) data.get("price_change_percentage_24h_in_currency");
		HashMap<String, String> price_change = getPrice(changes);
		cd.setPrice_percentage_change_in_24hr(price_change);

		DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		LocalDateTime now = LocalDateTime.now();
		LocalDateTime then = now.minusDays(7);
		String date = String.format(then.format(format));

		CryptoHistoryDTO historyDto = client.findHistory(id, date);
		Map<String, Object> historyData = historyDto.getMarket_data();
		HashMap<String, Object> historyRates = (HashMap<String, Object>) historyData.get("current_price");
		HashMap<String, String> history_price = getPrice(historyRates);
		cd.setLastWeek_price(history_price);
		return cd;
	}

	public HashMap<String, String> getPrice(HashMap<String, Object> obj) {
		List<String> currencies = new ArrayList<>(Arrays.asList("jpy", "aud", "usd"));
		HashMap<String, String> price = new HashMap<String, String>();
		for (String currency : obj.keySet()) {
			if (currencies.contains(currency)) {
				price.put(currency, obj.get(currency).toString());
			}
		}
		return price;
	}

	public String formatDate(String dateString) {
		LocalDate date = LocalDate.parse(dateString);
		String formattedDate = date.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
		return formattedDate;
	}

	public ArrayList<CryptoCurrency> getAll(String currency, String per_page, String page) {
		ArrayList<CryptoCurrency> ccs = client.findMarket(currency, per_page, page);
		for (int i = 0; i < ccs.size(); i++) {
			CryptoCurrency cc = ccs.get(i);
			Map<String, List<StatusUpdate>> updates = client.findStatusUpdate(cc.getId());
			List<StatusUpdate> statusData = updates.get("status_updates");
			for (int j = 0; j < statusData.size(); j++) {
				StatusUpdate update = statusData.get(j);
				update.setCreated_at(formatDate(update.getCreated_at().substring(0,10)));
			}
			cc.setStatusUpdates(updates.get("status_updates"));
		}
		return ccs;
	}

}
