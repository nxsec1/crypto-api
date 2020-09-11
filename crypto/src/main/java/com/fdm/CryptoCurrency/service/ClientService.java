package com.fdm.CryptoCurrency.service;

import com.fdm.CryptoCurrency.client.CryptoFeignClient;
import com.fdm.CryptoCurrency.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
// COMMENT: you need to be careful with putting all method as public

@Service
public class ClientService {

	private CryptoFeignClient client;

	@Autowired
	public ClientService(CryptoFeignClient client) {
		this.client = client;
	}

	public CryptoCurrencyDetail getCurrencyDetail(String id) {
		// COMMENTS: what happen if coingecko return 404 deal with client
		CryptoDetailDTO dto = client.findCurrency(id);

		//comment: make the format global constant
		DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		LocalDateTime now = LocalDateTime.parse(dto.getLast_updated(),DateTimeFormatter.ISO_DATE_TIME);
		LocalDateTime then = now.minusDays(7);
		String date = String.format(then.format(format));

		CryptoHistoryDTO historyDto = client.findHistory(id, date);

		CryptoCurrencyDetail cd = new CryptoCurrencyDetail().builder()
				.id(dto.getId()).symbol(dto.getSymbol()).name(dto.getName()).genesis_date(formatDate(dto.getGenesis_date())).last_update(formatDate(dto.getLast_updated().substring(0,10))).current_price(dto.getMarket_data().getCurrent_price()).market_cap(dto.getMarket_data().getMarket_cap()).price_percentage_change_in_24hr(dto.getMarket_data().getPrice_change_24h_in_currency()).lastWeek_price(historyDto.getMarket_data().getCurrent_price()).build();
		return cd;
	}


	private String formatDate(String dateString) {
		LocalDate date = LocalDate.parse(dateString);
		// COMMENT: put the date format as global constant
		String formattedDate = date.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
		return formattedDate;
	}

	public ArrayList<CryptoCurrency> getAll(String currency, String per_page, String page) {
		ArrayList<CryptoCurrency> ccs = client.findMarket(currency, per_page, page);
		for (int i = 0; i < ccs.size(); i++) {
			CryptoCurrency cc = ccs.get(i);
			StatusUpdateDTO updates = client.findStatusUpdate(cc.getId());
			List<StatusUpdate> statusData = updates.getStatus_updates();
			for (int j = 0; j < statusData.size(); j++) {
				StatusUpdate update = statusData.get(j);
				update.setCreated_at(formatDate(update.getCreated_at().substring(0,10)));
			}
			cc.setStatusUpdates(updates);
		}
		return ccs;
	}

}
