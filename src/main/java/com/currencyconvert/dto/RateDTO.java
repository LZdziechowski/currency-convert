package com.currencyconvert.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class RateDTO {

    private Date effectiveDate;
    private String currency;
    private String code;
    private BigDecimal mid;

    @Override
    public String toString() {
        return "RateDTO{" +
                "effectiveDate=" + effectiveDate +
                ", currency='" + currency + '\'' +
                ", code='" + code + '\'' +
                ", mid=" + mid +
                '}';
    }
}
