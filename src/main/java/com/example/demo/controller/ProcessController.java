package com.example.demo.controller;

import com.example.demo.controller.dto.ProcessRequest;
import com.example.demo.controller.dto.ProcessResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RestController
public class ProcessController {

    private WebClient webClient(String url) {
        return WebClient.builder().baseUrl(url).build();
    }

    @GetMapping("/process")
    public ResponseEntity<ProcessResponse> process() {

        System.out.println("Request received");

        Mono<ProcessResponse> responseMono1 = webClient("http://127.0.0.1:6000")
                .post()
                .uri("/process")
                .body(BodyInserters.fromValue(new ProcessRequest(42)))
                .retrieve()
                .bodyToMono(ProcessResponse.class);

        Mono<ProcessResponse> responseMono2 = webClient("http://127.0.0.1:6001")
                .post()
                .uri("/process")
                .body(BodyInserters.fromValue(new ProcessRequest(41)))
                .retrieve()
                .bodyToMono(ProcessResponse.class);

        Mono<Integer> resultMono = Mono.zip(responseMono1, responseMono2)
                .map(tuple -> tuple.getT1().getData() + tuple.getT2().getData());

        return resultMono
                .map(sum -> new ResponseEntity<>(new ProcessResponse(sum), HttpStatus.OK))
                .block();

    }

}
