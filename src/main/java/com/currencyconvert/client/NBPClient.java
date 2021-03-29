package com.currencyconvert.client;

import com.currencyconvert.dto.RatesTableDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class NBPClient {

    private final RestTemplate restTemplate;

    @Value("${nbp.api.endpoint}")
    private String nbpApiEndpoint;

    public List<RatesTableDTO> getCurrencyWithRatesFromTable(String table) {
        URI uri = UriComponentsBuilder.fromHttpUrl(nbpApiEndpoint + "/exchangerates/tables/" + table + "/")
                .queryParam("format", "json")
                .build().encode().toUri();
        RatesTableDTO[] ratesResponse = restTemplate.getForObject(uri, RatesTableDTO[].class);
        return Optional.ofNullable(ratesResponse)
                .map(Arrays::asList)
                .orElse(Collections.emptyList());
    }
}
