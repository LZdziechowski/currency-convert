package com.currencyconvert.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class ConvertDTO {

    private final double amount;
    private final String fromCurrency;
    private final String toCurrency;
}
