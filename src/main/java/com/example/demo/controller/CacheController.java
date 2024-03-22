package com.example.demo.controller;

import com.example.demo.data.CacheableEntity;
import com.example.demo.service.RedisService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/cache/")
public class CacheController {

    private final RedisService redisService;

    public CacheController(RedisService redisService) {
        this.redisService = redisService;
    }

    @PostMapping(path = "{id}")
    public ResponseEntity<Void> store(@PathVariable String id, @RequestBody CacheableEntity entity) {
        redisService.saveData(id, entity);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<CacheableEntity> store(@PathVariable String id) {
        try {
            CacheableEntity data = redisService.getData(id);
            return new ResponseEntity<>(data, HttpStatus.OK);
        }
        catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


}
