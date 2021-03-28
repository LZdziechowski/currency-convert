package com.currencyconvert.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class RatesTableDTO {

    @JsonProperty("table")
    private String table;

    @JsonProperty("effectiveDate")
    private String effectiveDate;

    @JsonProperty("rates")
    private List<RateDTO> rates;
}
