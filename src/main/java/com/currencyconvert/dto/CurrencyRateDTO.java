package com.currencyconvert.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class CurrencyRateDTO {

    @JsonProperty("currency")
    private String currency;

    @JsonProperty("code")
    private String code;

    @JsonProperty("mid")
    private String mid;
}
