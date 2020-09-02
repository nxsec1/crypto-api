package com.fdm.CryptoCurrency.client;
import java.util.ArrayList;

import com.fdm.CryptoCurrency.api.StatusUpdate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CryptoCurrency {
	private String id;
	private String current_price;
	private String market_cap;
	private ArrayList<StatusUpdate> statusUpdates;
}
