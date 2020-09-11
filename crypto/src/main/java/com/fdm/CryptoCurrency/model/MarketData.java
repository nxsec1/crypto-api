package com.fdm.CryptoCurrency.model;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class MarketData {
    private CurrencyPrice current_price;
    private CurrencyPrice market_cap;
    private CurrencyPrice price_change_24h_in_currency;
}
