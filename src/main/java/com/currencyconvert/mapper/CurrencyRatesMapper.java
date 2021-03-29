package com.currencyconvert.mapper;

import com.currencyconvert.domain.CurrencyRate;
import com.currencyconvert.domain.RatesTable;
import com.currencyconvert.dto.CurrencyRateDTO;
import com.currencyconvert.dto.RatesTableDTO;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CurrencyRatesMapper {

    public CurrencyRate mapToRate(CurrencyRateDTO currencyRateDTO) {
        return new CurrencyRate(currencyRateDTO.getCurrency(), currencyRateDTO.getCode(), new BigDecimal(currencyRateDTO.getMid()));
    }

    public CurrencyRateDTO mapToRateDTO(CurrencyRate currencyRate) {
        return new CurrencyRateDTO(currencyRate.getCurrency(), currencyRate.getCode(), currencyRate.getMid().toString());
    }

    public List<CurrencyRate> mapToRateList(List<CurrencyRateDTO> currencyRateDTOList) {
        return currencyRateDTOList.stream()
                .map(this::mapToRate)
                .collect(Collectors.toList());
    }

    public List<CurrencyRateDTO> mapToRateDTOList(List<CurrencyRate> currencyRateList) {
        return currencyRateList.stream()
                .map(this::mapToRateDTO)
                .collect(Collectors.toList());
    }

    public RatesTable mapToRatesTable(RatesTableDTO ratesTableDTO) {
        return new RatesTable(
                ratesTableDTO.getTable(),
                LocalDate.parse(ratesTableDTO.getEffectiveDate()),
                mapToRateList(ratesTableDTO.getRates()));
    }

    public RatesTableDTO mapToRatesTableDTO(RatesTable ratesTable) {
        return new RatesTableDTO(
                ratesTable.getTable(),
                ratesTable.getEffectiveDate().toString(),
                mapToRateDTOList(ratesTable.getCurrencyRates()));
    }

    public List<RatesTable> mapToRatesTableList(List<RatesTableDTO> ratesTableDTOList) {
        return ratesTableDTOList.stream()
                .map(this::mapToRatesTable)
                .collect(Collectors.toList());
    }

    public List<RatesTableDTO> mapToRatesTableDTOList(List<RatesTable> ratesTableList) {
        return ratesTableList.stream()
                .map(this::mapToRatesTableDTO)
                .collect(Collectors.toList());
    }
 }
