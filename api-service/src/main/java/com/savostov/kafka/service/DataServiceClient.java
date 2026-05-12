package com.savostov.kafka.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class DataServiceClient {

    private final RestTemplate restTemplate;

    @Value("${data.service.url}")
    private String dataServiceUrl;

    public Object searchTasks(String query) {
        String url = dataServiceUrl + "/search?title=" + query;
        return restTemplate.getForObject(url, Object.class);
    }

    public Object getReports() {
        String url = dataServiceUrl + "/reports";
        return restTemplate.getForObject(url, Object.class);
    }
}
