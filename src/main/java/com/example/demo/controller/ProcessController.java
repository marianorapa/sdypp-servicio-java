package com.example.demo.controller;

import com.example.demo.controller.dto.ProcessResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ProcessController {

    @GetMapping("/process")
    public ResponseEntity<ProcessResponse> process() {
        var restTemplate = new RestTemplate();
        System.out.println("Request received");

        var response = restTemplate.getForEntity("http://127.0.0.1:6000/process", ProcessResponse.class);
        return new ResponseEntity<>(response.getBody(), response.getStatusCode());
    }

}
