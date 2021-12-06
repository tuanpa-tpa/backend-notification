package com.example.demo.controller;

import com.example.demo.model.token.TokenClient;
import com.example.demo.service.FCMService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class TokenController {

    public final FCMService fcmService;

    public TokenController(FCMService fcmService) {
        this.fcmService = fcmService;
    }

    @PostMapping("/notification/token")
    public void getTokenClient(@RequestBody TokenClient tokenClient) {
        fcmService.getTk(tokenClient);
    }
}

