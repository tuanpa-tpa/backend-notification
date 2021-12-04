package com.example.demo.service;

import com.example.demo.model.DirectNotification;
import com.example.demo.model.SubscriptionRequest;
import com.example.demo.model.TopicNotification;
import com.google.firebase.messaging.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FCMService {
//    public String sendNotificationToTarget(DirectNotification notification) {
//        Message message = Message.builder()
//                .setToken(notification.getTarget())
//                .setNotification(new Notification(notification.getTitle(), notification.getMessage()))
//                .putData("content", notification.getTitle())
//                .build();
//        String response = null;
//        try {
//            response = FirebaseMessaging.getInstance().send(message);
//        } catch (FirebaseMessagingException e) {
////            log.error("Fail to send firebase notification", e);
//        }
//        return response;
//    }
    public void sendNotificationToTarget(DirectNotification notification) {
        Message message = Message.builder()
                .setWebpushConfig(WebpushConfig.builder()
                        .setNotification(WebpushNotification.builder()
                                        .setTitle(notification.getTitle())
                                        .setBody(notification.getMessage())
//                                        .setIcon("https://assets.mapquestapi.com/icon/v2/circle@2x.png")
                                        .build()
                        ).build()
                )
                .setToken(notification.getTarget())
                .build();
        FirebaseMessaging.getInstance().sendAsync(message);
    }

    public void sendNotificationToTopic(TopicNotification notification) {
        Message message = Message.builder()
                .setWebpushConfig(WebpushConfig.builder()
                                .setNotification(WebpushNotification.builder()
                                                .setTitle(notification.getTitle())
                                                .setBody(notification.getMessage())
                                                .setIcon("https://assets.mapquestapi.com/icon/v2/incident@2x.png")
                                                .build()
                                ).build()
                ).setTopic(notification.getTopic())
                .build();
        FirebaseMessaging.getInstance().sendAsync(message);
    }

    public void subscribeToTopic(SubscriptionRequest subscription) throws FirebaseMessagingException {
        FirebaseMessaging.getInstance().subscribeToTopic(List.of(subscription.getSubscriber()), subscription.getTopic());
    }


}