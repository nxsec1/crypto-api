package com.fdm.CryptoCurrency.apiTest;

import com.fdm.CryptoCurrency.client.CryptoFeignClient;
import com.fdm.CryptoCurrency.model.*;
import com.fdm.CryptoCurrency.service.ClientService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;


public class CoinServiceTest {

	private static final String AUS_DATE_FORMAT = "dd-MM-yyyy";
	private static final DateTimeFormatter AUS_DATE_FORMATTER = DateTimeFormatter.ofPattern(AUS_DATE_FORMAT);

	private  ClientService clientService;

	@Mock
	private CryptoFeignClient cryptoFeignClient;

	@Before
	public void setup(){
		initMocks(this);
		clientService = new ClientService(cryptoFeignClient);
	}

	@Test
	public void test_getCurrencyDetail_returns_cd() throws Exception {
		CurrencyPriceDTO currencyPriceDTO = new CurrencyPriceDTO().builder()
				.aud("12.23")
				.usd("2.3")
				.jpy("2342")
				.build();
		MarketDataDTO marketDataDTO = new MarketDataDTO().builder().market_cap(currencyPriceDTO).current_price(currencyPriceDTO).price_change_24h_in_currency(currencyPriceDTO).build();
		CryptoDetailDTO dto = new CryptoDetailDTO().builder()
				.id("bitcoin")
				.symbol("bitcoin")
				.name("bitcoin")
				.genesis_date("2020-09-14")
				.last_updated("2020-09-14T01:24:18.219Z")
				.market_data(marketDataDTO).build();

		HistoryMarketDataDTO historyMarketDataDTO = new HistoryMarketDataDTO().builder().current_price(currencyPriceDTO).build();
		CryptoHistoryDTO historyDTOdto = new CryptoHistoryDTO().builder().market_data(historyMarketDataDTO).build();

		LocalDate lastWeek = LocalDate.now().minusWeeks(1);
		String date = AUS_DATE_FORMATTER.format(lastWeek);

		when(cryptoFeignClient.findCurrency("bitcoin")).thenReturn(dto);
		when(cryptoFeignClient.findHistory("bitcoin",date)).thenReturn(historyDTOdto);

		CryptoCurrencyDetail cd = clientService.getCurrencyDetail("bitcoin");

		assertEquals("bitcoin", cd.getId());
		assertEquals("bitcoin", cd.getSymbol());
		assertEquals("bitcoin", cd.getName());
		assertNotNull(cd.getMarket_cap());
		assertNotNull(cd.getGenesis_date());
		assertNotNull(cd.getLast_update());
		assertNotNull(cd.getCurrent_price());
		assertNotNull(cd.getPrice_percentage_change_in_24hr());
		assertNotNull(cd.getLastWeek_price());

	}

	@Test
	public void test_getAll_returns_ccs() throws Exception {
		StatusUpdate statusUpdate = new StatusUpdate().builder().user_title("abc").created_at("2020-09-14").description("for testing").build();
		ArrayList<StatusUpdate> statusUpdates = new ArrayList<StatusUpdate>();
		statusUpdates.add(statusUpdate);
		StatusUpdateDTO statusUpdateDTO = new StatusUpdateDTO().builder().status_updates(statusUpdates).build();
		CryptoCurrency cryptoCurrency = new CryptoCurrency().builder().id("bitcoin").current_price("123").market_cap("345677").build();
		ArrayList<CryptoCurrency> ccs = new ArrayList<CryptoCurrency>();
		ccs.add(cryptoCurrency);
		when(cryptoFeignClient.findStatusUpdate("bitcoin")).thenReturn(statusUpdateDTO);

		when(cryptoFeignClient.findMarket("aud","10","1")).thenReturn(ccs);

		ArrayList<CryptoCurrency> cryptoCurrencys = clientService.getAll("aud", "10", "1");
		CryptoCurrency cc = cryptoCurrencys.get(0);
		assertEquals("bitcoin", cc.getId());
		assertNotNull(cc.getCurrent_price());
		assertNotNull(cc.getMarket_cap());

	}

}
