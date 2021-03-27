package com.currencyconvert.controller;

import com.currencyconvert.client.NbpClient;
import com.currencyconvert.dto.RateDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/app/nbp")
@RequiredArgsConstructor
public class NBPController {

    private final NbpClient nbpClient;

    @GetMapping("getRates")
    public void getRates() {
        List<RateDTO> rateDTOList = nbpClient.getRates();

        rateDTOList.forEach(System.out::println);
    }
}
