package com.currencyconvert.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class ToConvertDTO {

    private final String amount;
    private final String fromCurrency;
    private final String toCurrency;
}
