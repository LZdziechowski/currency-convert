package com.currencyconvert.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@Getter
public class RatesTable {

    private String table;
    private LocalDate effectiveDate;
    private List<Rate> rates;
}
