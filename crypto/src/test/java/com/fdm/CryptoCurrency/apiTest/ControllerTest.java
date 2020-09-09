package com.fdm.CryptoCurrency.apiTest;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.fdm.CryptoCurrency.controllers.CoinRestController;
import com.fdm.CryptoCurrency.exception.NotFoundCurrencyException;
import com.fdm.CryptoCurrency.exception.NotFoundPaginationException;
import com.fdm.CryptoCurrency.model.CryptoCurrency;
import com.fdm.CryptoCurrency.model.CurrencyDetail;
import com.fdm.CryptoCurrency.service.ClientService;

public class ControllerTest {
	
	
	private ClientService coinService;
	private CurrencyDetail mockCD;
	private CryptoCurrency mockCC;
	private CoinRestController coinRestController;
	
	
	@Before
	public void setUp() throws Exception{
		coinService = mock(ClientService.class);
		coinRestController = new CoinRestController(coinService);
		mockCD = mock(CurrencyDetail.class);
		mockCC = mock(CryptoCurrency.class);
	}
	
	
	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void that_getCurrencyDetail_returns_cd() {
		when(coinService.getCurrencyDetail("bitcoin")).thenReturn(mockCD);
		CurrencyDetail cd = coinRestController.getCurrencyDetail("bitcoin");
		assertEquals(mockCD,cd);
	}
	
	@Test
	public void that_getAll_returns_all() throws NotFoundCurrencyException, NotFoundPaginationException {
		ArrayList<CryptoCurrency> mockCCs = new ArrayList<CryptoCurrency>();
		mockCCs.add(mockCC);
		when(coinService.getAll("usd","10","1")).thenReturn(mockCCs);
		ArrayList<CryptoCurrency> cryptoCurrencys = coinRestController.getAll("usd","10","1");
		assertEquals(cryptoCurrencys,mockCCs);
	}
	
	@Test(expected = NotFoundCurrencyException.class)
	public void that_getAll_notFoundCurrency() throws NotFoundCurrencyException, NotFoundPaginationException {
		coinRestController.getAll("abc","10","1");
		
	}
	
	@Test(expected = NotFoundPaginationException.class)
	public void that_getAll_notFoundPage() throws Exception {
		coinRestController.getAll("aud","10","11");

	}
	
	
}
