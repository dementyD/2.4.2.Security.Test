package com.example231.crud.service;

import com.example231.crud.dto.CoordsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.HashMap;

@Service
public class DaDataService {
    private RestTemplate restTemplate;
    private String postApi = "https://suggestions.dadata.ru/suggestions/api/4_1/rs/geolocate/postal_unit";
    @Value("${token}")
    private String token;

    @Autowired
    public DaDataService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String getPostDaData(CoordsDTO coordsDTO) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.setBearerAuth("Token " + token);

        HttpEntity<CoordsDTO> request = new HttpEntity<>(coordsDTO, headers);
        CoordsDTO response = restTemplate.postForObject(postApi, request, CoordsDTO.class);

        HashMap<String, String> map = (HashMap<String, String>) response.getSuggestions()[0];
        return map.get("unrestricted_value");
    }

}
/////penis