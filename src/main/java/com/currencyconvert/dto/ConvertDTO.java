package com.currencyconvert.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public final class ConvertDTO {

    private final double amount;
    private final String fromCurrency;
    private final String toCurrency;
}
