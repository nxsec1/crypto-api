package com.fdm.CryptoCurrency.client;

import com.fdm.CryptoCurrency.model.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

// COMMENT: try to use feign client configuration and feign client error decoder
//url could be set in the application.properties
	@FeignClient(value="cryptodata",url="https://api.coingecko.com/api/v3/")
public interface CryptoFeignClient {
	
	@GetMapping(value="/coins/{id}")
	CryptoDetailDTO findCurrency(@PathVariable String id);
	
	@GetMapping(value="/coins/{id}/history")
	CryptoHistoryDTO findHistory(@PathVariable String id,@RequestParam(name="date")String date);
	
	@GetMapping(value="/coins/markets")
	ArrayList<CryptoCurrency> findMarket(@RequestParam(name="vs_currency") String currency, @RequestParam(name="per_page") String per_page,@RequestParam(name="page") String page);
	
	@GetMapping(value="coins/{id}/status_updates")
	StatusUpdateDTO findStatusUpdate(@PathVariable String id);
}
