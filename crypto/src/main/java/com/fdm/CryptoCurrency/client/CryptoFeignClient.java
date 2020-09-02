package com.fdm.CryptoCurrency.client;

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

}
