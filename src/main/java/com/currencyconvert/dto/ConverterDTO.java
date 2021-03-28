package com.currencyconvert.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@RequiredArgsConstructor
@Getter
public class ConverterDTO {

    private final BigDecimal amount;
    private final String fromCurrency;
    private final String toCurrency;
}
