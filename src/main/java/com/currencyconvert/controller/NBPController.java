package com.currencyconvert.controller;

import com.currencyconvert.dto.RatesTableDTO;
import com.currencyconvert.mapper.CurrencyRatesMapper;
import com.currencyconvert.service.NBPService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/app/nbp")
@RequiredArgsConstructor
public class NBPController {

    private final NBPService nbpService;
    private final CurrencyRatesMapper currencyRatesMapper;

    @GetMapping("getRates")
    public List<String> getRates() {
        return nbpService.getAvailableCurrencyWithRates();
    }

    @GetMapping("getCurrency")
    public List<String> getAvailableCurrency() {
        return nbpService.getAvailableCurrency();
    }
}
