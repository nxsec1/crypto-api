package com.fdm.CryptoCurrency.service;

import com.fdm.CryptoCurrency.client.CryptoFeignClient;
import com.fdm.CryptoCurrency.constants.AvailableCurrenciesConstants;
import com.fdm.CryptoCurrency.exception.NotFoundCurrencyException;
import com.fdm.CryptoCurrency.exception.NotFoundPaginationException;
import com.fdm.CryptoCurrency.model.*;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class ClientService {

	private CryptoFeignClient client;
	private static final String AUS_DATE_FORMAT = "dd-MM-yyyy";
	private static final DateTimeFormatter AUS_DATE_FORMATTER = DateTimeFormatter.ofPattern(AUS_DATE_FORMAT);

	@Autowired
	public ClientService(CryptoFeignClient client) {
		this.client = client;
	}

	public CryptoCurrencyDetail getCurrencyDetail(String id) throws Exception {
		new CryptoCurrencyDetail();
		CryptoCurrencyDetail cd;
		try {
			CryptoDetailDTO dto = client.findCurrency(id);
			LocalDate lastWeek = LocalDate.now().minusWeeks(1);
			String date = AUS_DATE_FORMATTER.format(lastWeek);
			CryptoHistoryDTO historyDto = client.findHistory(id, date);
			cd = new CryptoCurrencyDetail().builder()
					.id(dto.getId()).symbol(dto.getSymbol()).name(dto.getName()).genesis_date(formatDate(dto.getGenesis_date())).last_update(formatDate(dto.getLast_updated().substring(0,10))).current_price(dto.getMarket_data().getCurrent_price()).market_cap(dto.getMarket_data().getMarket_cap()).price_percentage_change_in_24hr(dto.getMarket_data().getPrice_change_24h_in_currency()).lastWeek_price(historyDto.getMarket_data().getCurrent_price()).build();
		}catch (FeignException e){
			throw new NotFoundCurrencyException(id+" Currency Not Found!");
		}
	return cd;
	}

	private String formatDate(String dateString) {
		LocalDate date = LocalDate.parse(dateString);
		String formattedDate = AUS_DATE_FORMATTER.format(date);
		return formattedDate;
	}

	public ArrayList<CryptoCurrency> getAll(String currency, String per_page, String page) throws Exception {

		try {
			AvailableCurrenciesConstants currencyName = AvailableCurrenciesConstants.valueOf(currency.toUpperCase());
			currency = currencyName.value();
		} catch (IllegalArgumentException ex) {
			throw new NotFoundCurrencyException(currency+" Currency Not Found!");
		}

		if(Integer.parseInt(per_page) < 1 || Integer.parseInt(per_page) > 10) {
			throw new NotFoundPaginationException(per_page+"Pagination Not Found! The pagination should be [1,10].");
		}
		ArrayList<CryptoCurrency> ccs;

		try {
			ccs = client.findMarket(currency, per_page, page);
			for (int i = 0; i < ccs.size(); i++) {
				CryptoCurrency cc = ccs.get(i);
				StatusUpdateDTO updates = client.findStatusUpdate(cc.getId());
				List<StatusUpdate> statusData = updates.getStatus_updates();
				for (int j = 0; j < statusData.size(); j++) {
					StatusUpdate update = statusData.get(j);
					update.setCreated_at(formatDate(update.getCreated_at().substring(0, 10)));
				}
				cc.setStatusUpdates(updates);
			}
		}catch (FeignException e) {
			throw new NotFoundCurrencyException(currency + " Currency Not Found!");
		}
		return ccs;
	}

}
