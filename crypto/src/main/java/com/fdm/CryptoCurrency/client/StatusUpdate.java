package com.fdm.CryptoCurrency.client;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StatusUpdate {
	private String user_title;
	private String description;
	private String created_at;
}
