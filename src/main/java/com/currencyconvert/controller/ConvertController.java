package com.currencyconvert.controller;

import com.currencyconvert.domain.Convert;
import com.currencyconvert.dto.ConvertDTO;
import com.currencyconvert.mapper.ConvertMapper;
import com.currencyconvert.service.ConvertService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/app")
@RequiredArgsConstructor
public class ConvertController {

    private final ConvertService convertService;
    private final ConvertMapper convertMapper;

    @GetMapping("convert")
    public String getConvert(
            @RequestParam(name = "amount") double amount,
            @RequestParam(name = "fromCurrency") String fromCurrency,
            @RequestParam(name = "toCurrency") String toCurrency) {
        Convert convert = convertMapper.mapToConvert(new ConvertDTO(amount, fromCurrency, toCurrency));
        return convertService.convertCurrency(convert);
    }

    @ExceptionHandler(NumberFormatException.class)
    public String numberFormatExceptionHandler() {
        return "Given amount must be a number";
    }

    @ExceptionHandler(IllegalStateException.class)
    public String illegalStateExceptionHandler(IllegalStateException e) {
        return e.getMessage();
    }
}
