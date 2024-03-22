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
        System.out.println("Request received");

        ProcessResponse response = new ProcessResponse(10);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
