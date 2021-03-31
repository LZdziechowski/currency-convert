package com.currencyconvert.service;

import com.currencyconvert.client.NBPClient;
import com.currencyconvert.config.NBPConfig;
import com.currencyconvert.domain.CurrencyRate;
import com.currencyconvert.domain.RatesTable;
import com.currencyconvert.dto.RatesTableDTO;
import com.currencyconvert.mapper.CurrencyRatesMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class NBPService {

    private final NBPClient nbpClient;
    private final CurrencyRatesMapper currencyRatesMapper;
    private final NBPConfig nbpConfig;

    public List<RatesTable> getAvailableCurrencyRates() {
            List<RatesTableDTO> ratesResponse = Stream.of(new RatesTableDTO[]{}).collect(Collectors.toList());
            for (String tables : nbpConfig.getNbpRatesTables()) {
                ratesResponse.addAll(nbpClient.getCurrencyWithRatesFromTable(tables));
            }
            return currencyRatesMapper.mapToRatesTableList(ratesResponse);
    }

    public List<String> getAvailableCurrency() {
        List<String> currencyFromNBP = getAvailableCurrencyRates().stream()
                .map(RatesTable::getCurrencyRates)
                .flatMap(Collection::stream)
                .map(CurrencyRate::getCurrency)
                .collect(Collectors.toList());
        currencyFromNBP.add("złoty (Polska)");
        return currencyFromNBP.stream()
                .map(String::toUpperCase)
                .sorted()
                .collect(Collectors.toList());
    }

    public List<String> getAvailableCurrencyWithRates() {
        List<String> resultMap = Stream.of(new String[]{}).collect(Collectors.toList());
        List<CurrencyRate> currencyWithRatesFromNBP = getAvailableCurrencyRates().stream()
                .map(RatesTable::getCurrencyRates)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
        for (CurrencyRate currencyRate : currencyWithRatesFromNBP) {
            resultMap.add(currencyRate.getCurrency() + ": " + currencyRate.getMid().toString());
        }
        return resultMap.stream()
                .map(String::toUpperCase)
                .sorted()
                .collect(Collectors.toList());
    }
}
