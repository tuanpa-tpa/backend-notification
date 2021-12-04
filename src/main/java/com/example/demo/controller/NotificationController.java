package com.example.demo.controller;

import com.example.demo.model.DirectNotification;
import com.example.demo.model.SubscriptionRequest;
import com.example.demo.model.TopicNotification;
import com.example.demo.service.FCMService;
import com.google.firebase.messaging.FirebaseMessagingException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NotificationController {
    public final FCMService fcmService;

    public NotificationController(FCMService fcmService) {
        this.fcmService = fcmService;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/notification")
    public void sendTargetedNotification(@RequestBody DirectNotification notification) {
        fcmService.sendNotificationToTarget(notification);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/topic/notification")
    public void sendNotificationToTopic(@RequestBody TopicNotification notification) {
        fcmService.sendNotificationToTopic(notification);
    }

//    @PostMapping("/topic/subscription")
//    public void subscribeToTopic(@RequestBody SubscriptionRequest subscription) throws FirebaseMessagingException {
//        fcmService.subscribeToTopic(subscription);
//    }

}
