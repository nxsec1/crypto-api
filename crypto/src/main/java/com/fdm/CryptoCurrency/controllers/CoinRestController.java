package com.fdm.CryptoCurrency.controllers;

import com.fdm.CryptoCurrency.exception.NotFoundCurrencyException;
import com.fdm.CryptoCurrency.exception.NotFoundPaginationException;
import com.fdm.CryptoCurrency.model.CryptoCurrency;
import com.fdm.CryptoCurrency.model.CryptoCurrencyDetail;
import com.fdm.CryptoCurrency.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
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
	public ArrayList<CryptoCurrency> getAll(@RequestParam(defaultValue = "aud",name="vs_currency") String currency, @RequestParam(defaultValue = "10",name="per_page") String per_page,@RequestParam(defaultValue = "1",name="page") String page) throws NotFoundCurrencyException, NotFoundPaginationException{
		// COMMENT: This should be a global constant instead of creating new list everytime the API receive a request
		// Also you can use enum instead of list
//		List<String> avaliableCurrencies = new ArrayList<>(Arrays.asList("jpy", "aud","usd"));
		// COMMENT: I feel like this logic should be in service
		// controller should not be aware of what currency available
		// it should only handle routing
//		if(!avaliableCurrencies.contains(currency.toLowerCase())) {
//			throw new NotFoundCurrencyException("Currency Not Found!");
//		}
//		if(Integer.parseInt(per_page)>10 || Integer.parseInt(per_page)<1) {
//			throw new NotFoundPaginationException();
//		}
		ArrayList<CryptoCurrency> cryptoCurrencys = clientService.getAll(currency,per_page,page);
		return cryptoCurrencys;
	}
	

	@GetMapping(value = "/coins/{id}")
	public CryptoCurrencyDetail getCurrencyDetail(@PathVariable String id) {
		// COMMENT: You dont need to assign to null
//		CryptoCurrencyDetail cryptoCurrencyDetail = null;
		CryptoCurrencyDetail cryptoCurrencyDetail = clientService.getCurrencyDetail(id);
		return cryptoCurrencyDetail;
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
