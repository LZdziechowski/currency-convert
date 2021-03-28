package com.currencyconvert.mapper;

import com.currencyconvert.domain.Rate;
import com.currencyconvert.domain.RatesTable;
import com.currencyconvert.dto.RateDTO;
import com.currencyconvert.dto.RatesTableDTO;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RatesMapper {

    public Rate mapToRate(RateDTO rateDTO) {
        return new Rate(rateDTO.getCurrency(), rateDTO.getCode(), new BigDecimal(rateDTO.getMid()));
    }

    public RateDTO mapToRateDTO(Rate rate) {
        return new RateDTO(rate.getCurrency(), rate.getCode(), rate.getMid().toString());
    }

    public List<Rate> mapToRateList(List<RateDTO> rateDTOList) {
        return rateDTOList.stream()
                .map(this::mapToRate)
                .collect(Collectors.toList());
    }

    public List<RateDTO> mapToRateDTOList(List<Rate> rateList) {
        return rateList.stream()
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
                mapToRateDTOList(ratesTable.getRates()));
    }
}
