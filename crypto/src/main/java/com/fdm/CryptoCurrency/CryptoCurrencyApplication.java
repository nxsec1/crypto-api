package com.fdm.CryptoCurrency;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class CryptoCurrencyApplication {

    public static void main(String[] args) {
        SpringApplication.run(CryptoCurrencyApplication.class, args);
    }

}
