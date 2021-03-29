package com.currencyconvert.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@Getter
public final class RatesTable {

    private final String table;
    private final LocalDate effectiveDate;
    private final List<CurrencyRate> currencyRates;
}
