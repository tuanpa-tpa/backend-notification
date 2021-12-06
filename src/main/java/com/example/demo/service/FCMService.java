package com.example.demo.service;

import com.example.demo.model.DirectNotification;
import com.example.demo.model.SubscriptionRequest;
import com.example.demo.model.TopicNotification;
import com.example.demo.model.token.TokenClient;
import com.google.firebase.messaging.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
@Slf4j
public class FCMService {
    private String token;
    private String sendAndGetResponse(Message message) throws InterruptedException, ExecutionException {
        return FirebaseMessaging.getInstance().sendAsync(message).get();
    }


    public void sendNotificationToTarget(DirectNotification notification) throws ExecutionException, InterruptedException {
        notification.setTarget(token);
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
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String jsonOutput = gson.toJson(message);
        String response = sendAndGetResponse(message);
        log.info(jsonOutput + "\n" + response);
    }

    public void sendNotificationToTopic(TopicNotification notification) {
        Message message = Message.builder()
                .setWebpushConfig(WebpushConfig.builder()
                                .setNotification(WebpushNotification.builder()
                                                .setTitle(notification.getTitle())
                                                .setBody(notification.getMessage())
//                                                .setIcon("https://assets.mapquestapi.com/icon/v2/incident@2x.png")
                                                .build()
                                ).build()
                ).setTopic(notification.getTopic())
                .build();
        FirebaseMessaging.getInstance().sendAsync(message);
    }

    public void subscribeToTopic(SubscriptionRequest subscription) throws FirebaseMessagingException {
        FirebaseMessaging.getInstance().subscribeToTopic(List.of(subscription.getSubscriber()), subscription.getTopic());
    }


    public void getTk(TokenClient tokenClient) {
        this.token = tokenClient.getToken();
    }
}