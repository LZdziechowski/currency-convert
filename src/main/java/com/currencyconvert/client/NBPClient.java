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
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
@RequiredArgsConstructor
public class NBPClient {

    private final RestTemplate restTemplate;

    @Value("${nbp.api.endpoint}")
    private String nbpApiEndpoint;

    @Value("${nbp.api.tables}")
    private List<String> nbpRatesTables;


    public List<RatesTableDTO> getRatesFromTable(String table) {
        URI uri = UriComponentsBuilder.fromHttpUrl(nbpApiEndpoint + "/exchangerates/tables/" + table + "/")
                .queryParam("format", "json")
                .build().encode().toUri();
        RatesTableDTO[] ratesResponse = restTemplate.getForObject(uri, RatesTableDTO[].class);
        return Optional.ofNullable(ratesResponse)
                .map(Arrays::asList)
                .orElse(Collections.emptyList());
    }

    public List<RatesTableDTO> getRates() {
        List<RatesTableDTO> ratesResponse = Stream.of(new RatesTableDTO[]{}).collect(Collectors.toList());
        for (String tables : nbpRatesTables) {
            ratesResponse.addAll(getRatesFromTable(tables));
        }
        return ratesResponse;
    }
}
