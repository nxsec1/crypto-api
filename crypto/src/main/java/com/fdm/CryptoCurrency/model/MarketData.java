package com.fdm.CryptoCurrency.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MarketData {
    private CurrencyPrice current_price;
    private CurrencyPrice market_cap;
    private CurrencyPrice price_change_24h_in_currency;
}
