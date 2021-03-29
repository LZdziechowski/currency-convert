package com.currencyconvert.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@RequiredArgsConstructor
@Getter
public final class Convert {

    private final BigDecimal amount;
    private final String fromCurrency;
    private final String toCurrency;
}
