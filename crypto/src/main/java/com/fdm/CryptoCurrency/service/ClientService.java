package com.fdm.CryptoCurrency.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdm.CryptoCurrency.api.CurrencyDetail;
import com.fdm.CryptoCurrency.client.CryptoDetailDTO;
import com.fdm.CryptoCurrency.client.CryptoFeignClient;

@Service
public class ClientService {

	private CryptoFeignClient client;
	
	@Autowired
	public ClientService(CryptoFeignClient client) {
		this.client = client;
	}

	public CurrencyDetail getCurrencyDetail(String id) {
		System.out.println("#### here i AM!!");
		CurrencyDetail cd = new CurrencyDetail();
		System.out.println(id);
		CryptoDetailDTO dto = client.findCurrency(id);
		System.out.println(dto);
		
		
		return cd;
	}
	

}
