package org.example.springcloudgateway.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GatewayTestController {

    @GetMapping("/test-gate")
    public String test() {
        return "Hello, World form Gateway!";
    }
}
