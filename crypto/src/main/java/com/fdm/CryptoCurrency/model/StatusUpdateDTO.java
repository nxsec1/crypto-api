package com.fdm.CryptoCurrency.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StatusUpdateDTO {
    List<StatusUpdate> status_updates;
}
