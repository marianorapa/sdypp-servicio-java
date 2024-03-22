package com.example.demo.data;

import java.io.Serializable;

public class CacheableEntity implements Serializable {
    private String name;
    private String status;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public CacheableEntity() {
    }

    public CacheableEntity(String name, String status) {
        this.name = name;
        this.status = status;
    }
}
