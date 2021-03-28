package com.currencyconvert.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@RequiredArgsConstructor
@Getter
public class ToConvertDTO {

    private final BigDecimal amount;
    private final String fromCurrency;
    private final String toCurrency;
}
