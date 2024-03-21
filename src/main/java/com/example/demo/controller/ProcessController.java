package com.example.demo.controller;

import com.example.demo.controller.dto.ProcessRequest;
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
        var request = new ProcessRequest(42);
        var response = restTemplate.postForEntity("http://127.0.0.1:6000/process", request, ProcessResponse.class);

        ProcessRequest request2 = new ProcessRequest(41);
        var response2 = restTemplate.postForEntity("http://127.0.0.1:6001/process", request2, ProcessResponse.class);

        var finalResponse = new ProcessResponse(response.getBody().getData() + response2.getBody().getData());
        return new ResponseEntity<>(finalResponse, HttpStatus.OK);
    }

}
