package com.fdm.CryptoCurrency.apiTest;

import com.fdm.CryptoCurrency.controllers.CoinRestController;
import com.fdm.CryptoCurrency.exception.NotFoundCurrencyException;
import com.fdm.CryptoCurrency.exception.NotFoundPaginationException;
import com.fdm.CryptoCurrency.model.CryptoCurrency;
import com.fdm.CryptoCurrency.model.CryptoCurrencyDetail;
import com.fdm.CryptoCurrency.service.ClientService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ControllerTest {
	
	
	private ClientService coinService;
	private CryptoCurrencyDetail mockCD;
	private CryptoCurrency mockCC;
	private CoinRestController coinRestController;
	
	
	@Before
	public void setUp() throws Exception{
		coinService = mock(ClientService.class);
		coinRestController = new CoinRestController(coinService);
		mockCD = mock(CryptoCurrencyDetail.class);
		mockCC = mock(CryptoCurrency.class);
	}
	
	
	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void that_getCurrencyDetail_returns_cd() throws Exception {
		when(coinService.getCurrencyDetail("bitcoin")).thenReturn(mockCD);
		CryptoCurrencyDetail cd = coinRestController.getCurrencyDetail("bitcoin");
		assertEquals(mockCD,cd);
	}
	
	@Test
	public void that_getAll_returns_all() throws Exception {
		ArrayList<CryptoCurrency> mockCCs = new ArrayList<CryptoCurrency>();
		mockCCs.add(mockCC);
		when(coinService.getAll("usd","10","1")).thenReturn(mockCCs);
		ArrayList<CryptoCurrency> cryptoCurrencys = coinRestController.getAll("usd","10","1");
		assertEquals(cryptoCurrencys,mockCCs);
	}
	
	@Test(expected = NotFoundCurrencyException.class)
	public void that_getAll_notFoundCurrency() throws Exception {
		coinRestController.getAll("abc","10","1");
		
	}
	
	@Test(expected = NotFoundPaginationException.class)
	public void that_getAll_notFoundPage() throws Exception {
		coinRestController.getAll("aud","10","11");

	}
	
	
}
