package com.fdm.CryptoCurrency.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fdm.CryptoCurrency.exception.NotFoundCurrencyException;
import com.fdm.CryptoCurrency.exception.NotFoundPaginationException;
import com.fdm.CryptoCurrency.model.CryptoCurrency;
import com.fdm.CryptoCurrency.model.CurrencyDetail;
import com.fdm.CryptoCurrency.service.ClientService;

@RestController
public class CoinRestController {
	private ClientService clientService;
	
	@Autowired
	public CoinRestController(ClientService clientService) {
		this.clientService = clientService;
	}
	
	@GetMapping(value = "coins/markets")
	public ArrayList<CryptoCurrency> getAll(@RequestParam(defaultValue = "aud",name="vs_currency") String currency, @RequestParam(defaultValue = "10",name="per_page") String per_page,@RequestParam(defaultValue = "1",name="page") String page) throws NotFoundCurrencyException, NotFoundPaginationException{
		List<String> avaliableCurrencies = new ArrayList<>(Arrays.asList("jpy", "aud","usd"));
		if(!avaliableCurrencies.contains(currency.toLowerCase())) {
			throw new NotFoundCurrencyException("Currency Not Found!");
		}
		if(Integer.parseInt(per_page)>10 || Integer.parseInt(per_page)<1) {
			throw new NotFoundPaginationException();
		}
		ArrayList<CryptoCurrency> cryptoCurrencys = clientService.getAll(currency,per_page,page);
		return cryptoCurrencys;
	}
	
	
	@GetMapping(value = "/coins/{id}")
	public CurrencyDetail getCurrencyDetail(@PathVariable String id) {
		CurrencyDetail currencyDetail = null;
		currencyDetail = clientService.getCurrencyDetail(id);
		return currencyDetail;
	}	
	
	@ExceptionHandler(NotFoundCurrencyException.class)
	public String notFoundCurrency() {
		return "Currency Not Found! \nThe availiable currencies are JPY ,AUD , USD. \nTry http://localhost:8080/coins/markets instead. \nThanks!";
	}
	
	@ExceptionHandler(NotFoundPaginationException.class)
	public String notFoundPage() {
		return "Page Not Found! \nThe availiable pagination numbers are [1,10] \nTry http://localhost:8080/coins/markets instead. \nThanks!";
	}
	
}
