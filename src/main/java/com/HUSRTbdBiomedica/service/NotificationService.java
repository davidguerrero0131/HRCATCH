package com.HUSRTbdBiomedica.service;


import org.springframework.stereotype.Service;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;

@Service
public class NotificationService {

	private FirebaseMessaging firebaseMessaging;

    public void FirebaseMessagingService(FirebaseMessaging firebaseMessaging) {
        this.firebaseMessaging = firebaseMessaging;
    }


    public String sendNotification(String title, String body, String token) throws FirebaseMessagingException {

        Notification notification = Notification
                .builder()
                .setTitle(title)
                .setBody(body)
                .build();

        Message message = Message
                .builder()
                .setToken(token)
                .setNotification(notification)
//              .putAllData(note.getData())
                .build();
        return firebaseMessaging.send(message);
    }
}
