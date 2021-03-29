package com.currencyconvert.service;

import com.currencyconvert.domain.Convert;
import com.currencyconvert.domain.CurrencyRate;
import com.currencyconvert.domain.RatesTable;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collection;

@Service
@RequiredArgsConstructor
public class ConvertService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConvertService.class);

    private final NBPService nbpService;

    public String convertCurrency(Convert convert) {
        LOGGER.info("Getting rate for: " + convert.getFromCurrency());
        BigDecimal currencyRateFrom = getCurrencyRate(convert.getFromCurrency());
        LOGGER.info("Getting rate for: " + convert.getToCurrency());
        BigDecimal currencyRateTo = getCurrencyRate(convert.getToCurrency());
        LOGGER.info("Starting conversion for amount: " + convert.getAmount().toString());
        BigDecimal resultAmount = convert.getAmount()
                .multiply(currencyRateFrom)
                .divide(currencyRateTo, 2, RoundingMode.HALF_EVEN);
        LOGGER.info("Conversion finished with result: " + resultAmount.toString());
        return resultAmount.toString();
    }

    private BigDecimal getCurrencyRate(String currency) {
        if (currency.equalsIgnoreCase("złoty (Polska)")) {
            LOGGER.info("Rate is: 1.0");
            return BigDecimal.ONE;
        }
        CurrencyRate currencyRate = nbpService.getAvailableCurrencyRates().stream()
                .map(RatesTable::getCurrencyRates)
                .flatMap(Collection::stream)
                .filter(cr -> cr.getCurrency().equalsIgnoreCase(currency))
                .findFirst().orElse(null);
        if (currencyRate == null) {
            LOGGER.error("Wrong currency name");
            LOGGER.info("Converting has been aborted");
            throw new IllegalStateException("Wrong currency name: " + currency);
        } else {
            LOGGER.info("Rate is: " + currencyRate.getMid().toString());
            return currencyRate.getMid();
        }
    }
}
