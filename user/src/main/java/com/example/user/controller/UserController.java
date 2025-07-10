package com.example.user.controller;


import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    public UserController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping
    @CircuitBreaker(name = "userService", fallbackMethod = "getAllUsersFallback")
    public List<String> getAllUsers() {
        String url = "http://localhost:9090/v1/orders";
        List<String> userList = restTemplate.getForObject(url, List.class);
        return userList;
    }

    public List<String> getAllUsersFallback(Exception e) {
        System.out.println("Fallback method called due to: " + e.getMessage());
        return List.of("Fallback User 1", "Fallback User 2");
    }
}