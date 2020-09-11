package com.fdm.CryptoCurrency.model;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class HistoryData {
    private CurrencyPrice current_price;
}
