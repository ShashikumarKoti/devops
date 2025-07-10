package com.example.catalog.catalog.controller;

import com.example.catalog.catalog.model.Order;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/v1/orders")
public class OrderController {

    @GetMapping("")
    public List<String> getOrders() {
        System.out.println("Catalog service called");
        return Arrays.asList("Alice", "Bob", "Charlie");
    }
}
