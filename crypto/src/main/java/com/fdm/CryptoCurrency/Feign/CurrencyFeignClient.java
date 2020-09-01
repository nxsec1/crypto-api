package com.fdm.CryptoCurrency.Feign;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.fdm.CryptoCurrency.api.CryptoCurrency;
import com.fdm.CryptoCurrency.api.CurrencyDetail;
import com.fdm.CryptoCurrency.exception.NotFoundCurrencyException;
import com.fdm.CryptoCurrency.exception.NotFoundPageException;
import com.fdm.CryptoCurrency.service.CoinService;

@RestController
public class CurrencyFeignClient implements CurrencyFeignAPI {
	private CoinService coinService;

	@Autowired
	public CurrencyFeignClient(CoinService coinService) {
		this.coinService = coinService;
	}

	@Override
	public CurrencyDetail findCurrencyDetail(String id) {
		CurrencyDetail currencyDetail = null;
		currencyDetail = coinService.getCurrencyDetail(id);
		return currencyDetail;
	}

	@Override
	public ArrayList<CryptoCurrency> findAll(String currency, String page)
			throws NotFoundCurrencyException, NotFoundPageException {
		List<String> avaliableCurrencies = new ArrayList<>(Arrays.asList("jpy", "aud", "usd"));
		if (!avaliableCurrencies.contains(currency.toLowerCase())) {
			throw new NotFoundCurrencyException("Currency Not Found!");
		}
		if (Integer.parseInt(page) > 10 || Integer.parseInt(page) < 1) {
			throw new NotFoundPageException();
		}
		ArrayList<CryptoCurrency> cryptoCurrencys = coinService.getAll(currency, page);
		return cryptoCurrencys;
	}

}
