package com.fdm.CryptoCurrency.client;

import java.util.ArrayList;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value="cryptodata",url="https://api.coingecko.com/api/v3")
public interface CryptoFeignClient {
	
	@GetMapping(value="/coins/{id}")
	CryptoDetailDTO findCurrency(@PathVariable String id);
	
	@GetMapping(value="/coins/{id}/history")
	CryptoHistoryDTO findHistory(@PathVariable String id,@RequestParam(name="date")String date);
	
	@GetMapping(value="/coins/markets")
	ArrayList<CryptoCurrency> findMarket(@RequestParam(name="vs_currency") String currency, @RequestParam(name="per_page") String per_page,@RequestParam(name="page") String page);
	
	
}
