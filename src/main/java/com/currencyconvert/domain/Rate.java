package com.currencyconvert.domain;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class Rate {

    private String currency;
    private String code;
    private BigDecimal mid;

    public Rate(String currency, String code, BigDecimal mid) {
        this.currency = currency;
        this.code = code;
        this.mid = mid;
    }
}
