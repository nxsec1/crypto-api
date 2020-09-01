package com.fdm.CryptoCurrency.Feign;

import java.util.ArrayList;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.fdm.CryptoCurrency.api.CryptoCurrency;
import com.fdm.CryptoCurrency.api.CurrencyDetail;
import com.fdm.CryptoCurrency.exception.NotFoundCurrencyException;
import com.fdm.CryptoCurrency.exception.NotFoundPageException;

@FeignClient(value="crypto",url="http://localhost:3000/")
public interface CurrencyFeignAPI {
	
	@GetMapping(value = "coins/markets")
	ArrayList<CryptoCurrency> findAll(@RequestParam(defaultValue = "aud",name="currency") String currency, @RequestParam(defaultValue = "1",name="page") String page) throws NotFoundCurrencyException, NotFoundPageException;
	
	@GetMapping(value = "coins/{id}")
	CurrencyDetail findCurrencyDetail(@PathVariable String id);
}
