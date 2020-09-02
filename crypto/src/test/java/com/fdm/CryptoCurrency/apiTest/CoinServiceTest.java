package com.fdm.CryptoCurrency.apiTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;

import org.junit.BeforeClass;
import org.junit.Test;

import com.fdm.CryptoCurrency.api.CurrencyDetail;
import com.fdm.CryptoCurrency.client.CryptoCurrency;
import com.fdm.CryptoCurrency.client.CryptoFeignClient;
import com.fdm.CryptoCurrency.service.ClientService;


public class CoinServiceTest {
	
	private static CryptoFeignClient client;
	private static ClientService coinService;
	
	@BeforeClass
	public static void init() {
		coinService = new ClientService(client);
	}
	
	
	@Test
	public void test_getCurrencyDetail_returns_cd() {
		CurrencyDetail cd = coinService.getCurrencyDetail("bitcoin");
		
		assertEquals("bitcoin",cd.getId());
		assertNotNull(cd.getSymbol());
		assertNotNull(cd.getName());
		assertNotNull(cd.getMarket_cap());
		assertNotNull(cd.getGenesis_date());
		assertNotNull(cd.getLast_update());
		assertNotNull(cd.getCurrent_price());
		assertNotNull(cd.getPrice_percentage_change_in_24hr());
		assertNotNull(cd.getLastWeek_price());

	}
	
	@Test
	public void test_getAll_returns_ccs() {
		ArrayList<CryptoCurrency> cryptoCurrencys = coinService.getAll("aud","10","1");
		CryptoCurrency cc = cryptoCurrencys.get(0);
		assertEquals("bitcoin",cc.getId());
		assertNotNull(cc.getCurrent_price());
		assertNotNull(cc.getMarket_cap());

	}
	
}
