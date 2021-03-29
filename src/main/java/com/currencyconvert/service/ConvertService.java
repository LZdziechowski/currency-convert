package com.currencyconvert.service;

import com.currencyconvert.domain.Convert;
import com.currencyconvert.domain.CurrencyRate;
import com.currencyconvert.domain.RatesTable;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collection;

@Service
@RequiredArgsConstructor
public class ConvertService {

    private final NBPService nbpService;

    public String convertCurrency(Convert convert) {
        BigDecimal currencyRateFrom = getCurrencyRate(convert.getFromCurrency());
        BigDecimal currencyRateTo = getCurrencyRate(convert.getToCurrency());
        BigDecimal resultAmount = convert.getAmount()
                .multiply(currencyRateFrom)
                .divide(currencyRateTo, 2, RoundingMode.HALF_EVEN);
        return resultAmount.toString();
    }

    private BigDecimal getCurrencyRate(String currency) {
        if (currency.equalsIgnoreCase("złoty (Polska)")) {
            return BigDecimal.ONE;
        }
        CurrencyRate currencyRate = nbpService.getAvailableCurrencyRates().stream()
                .map(RatesTable::getCurrencyRates)
                .flatMap(Collection::stream)
                .filter(cr -> cr.getCurrency().equalsIgnoreCase(currency))
                .findFirst().orElse(null);
        return currencyRate.getMid();
    }
}
