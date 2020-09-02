package com.fdm.CryptoCurrency.Feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.fdm.CryptoCurrency.api.CurrencyDetail;

@FeignClient(value="crypto",url="http://localhost:3000/")
public interface CurrencyFeignClient {

	@GetMapping(value = "status/{id}")
	CurrencyDetail findCurrencyDetail(@PathVariable String id);
}
