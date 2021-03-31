package com.currencyconvert.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Getter
public class NBPConfig {

    @Value("${nbp.api.tables}")
    private List<String> nbpRatesTables;
    @Value("${nbp.api.endpoint}")
    private String nbpApiEndpoint;
}
