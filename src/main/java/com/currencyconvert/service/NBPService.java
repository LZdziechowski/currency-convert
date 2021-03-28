package com.currencyconvert.service;

import com.currencyconvert.client.NBPClient;
import com.currencyconvert.dto.RateDTO;
import com.currencyconvert.dto.RatesTableDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NBPService {

    private final NBPClient nbpClient;

    public List<RatesTableDTO> getAvailableRates() {
        return nbpClient.getRates();
    }

    public List<String> getAvailableCurrency() {
        return nbpClient.getRates().stream()
                .map(RatesTableDTO::getRates)
                .flatMap(Collection::stream)
                .map(RateDTO::getCurrency)
                .map(String::toUpperCase)
                .collect(Collectors.toList());
    }
}
