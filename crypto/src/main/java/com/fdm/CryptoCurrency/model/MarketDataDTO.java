package com.fdm.CryptoCurrency.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MarketDataDTO {
    private CurrencyPriceDTO current_price;
    private CurrencyPriceDTO market_cap;
    private CurrencyPriceDTO price_change_24h_in_currency;
}
