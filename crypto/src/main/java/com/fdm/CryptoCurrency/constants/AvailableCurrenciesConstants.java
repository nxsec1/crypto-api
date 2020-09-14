package com.fdm.CryptoCurrency.constants;

import lombok.Getter;

public enum AvailableCurrenciesConstants {
    AUD("aud"),
    JPY("JPY"),
    USD("usd");

    @Getter
    private final String currency;

    AvailableCurrenciesConstants(final String currency){
        this.currency = currency;
    }

    public final String value(){
        return this.currency;
    }
}
