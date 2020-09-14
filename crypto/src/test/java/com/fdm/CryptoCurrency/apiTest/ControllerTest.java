package com.fdm.CryptoCurrency.apiTest;

import com.fdm.CryptoCurrency.controllers.CoinRestController;
import com.fdm.CryptoCurrency.model.CryptoCurrency;
import com.fdm.CryptoCurrency.model.CryptoCurrencyDetail;
import com.fdm.CryptoCurrency.service.ClientService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ControllerTest {

	private static final String CURRENCY_NOT_FOUND_EXCEPTION = "Currency Not Found!";

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
		ResponseEntity<?> response = coinRestController.getCurrencyDetail("bitcoin");
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(mockCD, response.getBody());
	}

	@Test
	public void that_getAll_returns_all() throws Exception {
		ArrayList<CryptoCurrency> mockCCs = new ArrayList<CryptoCurrency>();
		mockCCs.add(mockCC);
		when(coinService.getAll("usd","10","1")).thenReturn(mockCCs);
		ResponseEntity<?> response = coinRestController.getAll("usd","10","1");
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(response.getBody(),mockCCs);
	}
	
	
}
