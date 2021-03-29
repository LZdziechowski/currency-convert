package com.currencyconvert.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@RequiredArgsConstructor
@Getter
public final class CurrencyRate {

    private final String currency;
    private final String code;
    private final BigDecimal mid;
}
