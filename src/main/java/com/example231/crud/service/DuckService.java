package com.example231.crud.service;

import com.example231.crud.model.Avatar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class DuckService {
    private RestTemplate restTemplate;
    private String randomApi = "https://random-d.uk/api/random";

    @Autowired
    public DuckService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Avatar getAvatar() {
        return restTemplate.getForEntity(randomApi, Avatar.class).getBody();
    }
}
