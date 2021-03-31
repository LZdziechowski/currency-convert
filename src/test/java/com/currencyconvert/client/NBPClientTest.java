package com.currencyconvert.client;

import com.currencyconvert.config.NBPConfig;
import com.currencyconvert.dto.RatesTableDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class NBPClientTest {

    @InjectMocks
    private NBPClient nbpClient;
    @Mock
    private RestTemplate restTemplate;
    @Mock
    private NBPConfig nbpConfig;

    @Test
    void shouldGetCurrencyWithRatesFromTable() throws URISyntaxException {
        //Given
        when(nbpConfig.getNbpApiEndpoint()).thenReturn("http://test.com");
        RatesTableDTO[] ratesTableDTOS = new RatesTableDTO[1];
        ratesTableDTOS[0] = new RatesTableDTO("testTable", "testEffectiveDate", new ArrayList<>());
        URI url = new URI("http://test.com/exchangerates/tables/a/?format=json");
        when(restTemplate.getForObject(url, RatesTableDTO[].class)).thenReturn(ratesTableDTOS);
        //When
        List<RatesTableDTO> ratesTableDTOList = nbpClient.getCurrencyWithRatesFromTable("a");
        //Then
        assertEquals(1, ratesTableDTOList.size());
        assertEquals("testTable", ratesTableDTOList.get(0).getTable());
        assertEquals("testEffectiveDate", ratesTableDTOList.get(0).getEffectiveDate());
        assertEquals(new ArrayList<>(), ratesTableDTOList.get(0).getRates());
    }
}