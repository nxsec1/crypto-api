package com.fdm.CryptoCurrency.model;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CurrencyPrice {
    private String aud;
    private String jpy;
    private String usd;
}
