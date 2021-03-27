package com.currencyconvert.client;

import com.currencyconvert.dto.RateDTO;
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
public class NbpClient {

    private final RestTemplate restTemplate;

    @Value("${nbp.api.endpoint}")
    private final String nbpApiEndpoint;

    public List<RateDTO> getRates() {
        URI uri = UriComponentsBuilder.fromHttpUrl(nbpApiEndpoint + "/exchangerates/tables/a/")
                .queryParam("format", "json")
                .build().encode().toUri();
        RateDTO[] ratesResponse = restTemplate.getForObject(uri, RateDTO[].class);
        return Optional.ofNullable(ratesResponse)
                .map(Arrays::asList)
                .orElse(Collections.emptyList());
    }
}
