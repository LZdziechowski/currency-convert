package com.currencyconvert.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class RateDTO {

    @JsonProperty("currency")
    private String currency;

    @JsonProperty("code")
    private String code;

    @JsonProperty("mid")
    private BigDecimal mid;

    @Override
    public String toString() {
        return "RateDTO{" +
                "currency='" + currency + '\'' +
                ", code='" + code + '\'' +
                ", mid=" + mid +
                '}';
    }
}
