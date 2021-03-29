package com.currencyconvert.service;

import com.currencyconvert.client.NBPClient;
import com.currencyconvert.domain.CurrencyRate;
import com.currencyconvert.domain.RatesTable;
import com.currencyconvert.dto.RatesTableDTO;
import com.currencyconvert.mapper.CurrencyRatesMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
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

    @Value("${nbp.api.tables}")
    private List<String> nbpRatesTables;

    public List<RatesTable> getAvailableCurrencyRates() {
            List<RatesTableDTO> ratesResponse = Stream.of(new RatesTableDTO[]{}).collect(Collectors.toList());
            for (String tables : nbpRatesTables) {
                ratesResponse.addAll(nbpClient.getRatesFromTable(tables));
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
}
