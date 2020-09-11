package com.fdm.CryptoCurrency.controllers;

import com.fdm.CryptoCurrency.exception.NotFoundCurrencyException;
import com.fdm.CryptoCurrency.exception.NotFoundPaginationException;
import com.fdm.CryptoCurrency.model.CryptoCurrency;
import com.fdm.CryptoCurrency.model.CryptoCurrencyDetail;
import com.fdm.CryptoCurrency.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class CoinRestController {
	private ClientService clientService;
	
	@Autowired
	public CoinRestController(ClientService clientService) {
		this.clientService = clientService;
	}
	
	@GetMapping(value = "coins/markets")
	public ResponseEntity<ArrayList<CryptoCurrency>> getAll(@RequestParam(defaultValue = "aud",name="vs_currency") String currency, @RequestParam(defaultValue = "10",name="per_page") String per_page, @RequestParam(defaultValue = "1",name="page") String page) throws Exception {
		return ResponseEntity.ok(clientService.getAll(currency,per_page,page));
	}
	

	@GetMapping(value = "/coins/{id}")
	public ResponseEntity<CryptoCurrencyDetail> getCurrencyDetail(@PathVariable String id) throws Exception {
		return ResponseEntity.ok(clientService.getCurrencyDetail(id));
	}	
	
	@ExceptionHandler(NotFoundCurrencyException.class)
	public String notFoundCurrency() {
		return "Currency Not Found! \nThe available currencies are JPY ,AUD , USD. \nTry http://localhost:8080/coins/markets instead. \nThanks!";
	}
	
	@ExceptionHandler(NotFoundPaginationException.class)
	public String notFoundPage() {
		return "Page Not Found! \nThe available pagination numbers are [1,10] \nTry http://localhost:8080/coins/markets instead. \nThanks!";
	}
	
}
