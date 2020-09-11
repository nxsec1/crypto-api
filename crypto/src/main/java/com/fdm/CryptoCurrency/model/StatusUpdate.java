package com.fdm.CryptoCurrency.model;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class StatusUpdate {
	private String user_title;
	private String description;
	private String created_at;
}
