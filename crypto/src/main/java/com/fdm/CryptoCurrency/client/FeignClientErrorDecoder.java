package com.fdm.CryptoCurrency.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fdm.CryptoCurrency.exception.BadRequestException;
import com.fdm.CryptoCurrency.exception.NotFoundCurrencyException;
import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.SneakyThrows;

public class FeignClientErrorDecoder implements ErrorDecoder {

    private final ObjectMapper mapper = new ObjectMapper();

    @SneakyThrows
    @Override
    public Exception decode(String methodKey, Response response) {
//        log.error("Error Calling External APi... Status:{} Reason:{} Body:{}",response.status(),response.reason(),response.body());
        switch (response.status()){
            case 400:
                return new BadRequestException(response.reason());
            case 404:
                return new NotFoundCurrencyException(response.reason());
            default:
                return new Exception("Generic error");
        }
    }
}
