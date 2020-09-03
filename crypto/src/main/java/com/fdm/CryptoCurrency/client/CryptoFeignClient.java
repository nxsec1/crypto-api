package com.fdm.CryptoCurrency.client;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.fdm.CryptoCurrency.model.CryptoCurrency;
import com.fdm.CryptoCurrency.model.CryptoDetailDTO;
import com.fdm.CryptoCurrency.model.CryptoHistoryDTO;
import com.fdm.CryptoCurrency.model.StatusUpdate;

@FeignClient(value="cryptodata",url="localhost:8080")
public interface CryptoFeignClient {
	
	@GetMapping(value="/coins/{id}")
	CryptoDetailDTO findCurrency(@PathVariable String id);
	
	@GetMapping(value="/coins/{id}/history")
	CryptoHistoryDTO findHistory(@PathVariable String id,@RequestParam(name="date")String date);
	
	@GetMapping(value="/coins/markets")
	ArrayList<CryptoCurrency> findMarket(@RequestParam(name="vs_currency") String currency, @RequestParam(name="per_page") String per_page,@RequestParam(name="page") String page);
	
	@GetMapping(value="coins/{id}/status_updates")
	Map<String, List<StatusUpdate>> findStatusUpdate(@PathVariable String id);
}
