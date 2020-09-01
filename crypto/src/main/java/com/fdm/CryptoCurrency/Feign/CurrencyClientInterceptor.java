package com.fdm.CryptoCurrency.Feign;

import org.springframework.context.annotation.Configuration;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class CurrencyClientInterceptor implements RequestInterceptor{

	@Override
	public void apply(RequestTemplate template) {
		template.header("myKey","myValue");
		log.warn("### CUSTOM INTERCEPTOR USED");
		
	}

}
