package com.currencyconvert.mapper;

import com.currencyconvert.domain.Convert;
import com.currencyconvert.dto.ConvertDTO;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class ConvertMapper {

    public Convert mapToConvert(ConvertDTO convertDTO) {
        return new Convert(BigDecimal.valueOf(convertDTO.getAmount()), convertDTO.getFromCurrency(), convertDTO.getToCurrency());
    }
}
