package com.fdm.CryptoCurrency.Feign;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.fdm.CryptoCurrency.api.CurrencyDetail;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class CurrencyFeignController {
private final CurrencyFeignClient feignClient;

	@GetMapping(value = "coins/{id}")
	public CurrencyDetail findCurrencyDetail(@PathVariable String id) {
		return feignClient.findCurrencyDetail(id);
	};
	
}
