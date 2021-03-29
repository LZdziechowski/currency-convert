package com.currencyconvert.controller;

import com.currencyconvert.service.NBPService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.apache.log4j.Logger;

import java.util.List;

@RestController
@RequestMapping("/app/nbp")
@RequiredArgsConstructor
public class NBPController {

    private static final Logger LOGGER = Logger.getLogger(NBPController.class);

    private final NBPService nbpService;

    @GetMapping("getRates")
    public List<String> getRates() {
        LOGGER.info(("Getting all currency with rates value from NBP").toUpperCase());
        return nbpService.getAvailableCurrencyWithRates();
    }

    @GetMapping("getCurrency")
    public List<String> getAvailableCurrency() {
        LOGGER.info(("Getting all currency names NBP").toUpperCase());
        return nbpService.getAvailableCurrency();
    }
}
