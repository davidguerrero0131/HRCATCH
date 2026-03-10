package com.HUSRTbdBiomedica.app.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.HUSRTbdBiomedica.service.NotificationService;
import com.google.firebase.messaging.FirebaseMessagingException;

public class NotificactionController {

	@Autowired
    private NotificationService firebaseService;

    
    public void sendPushMessage() throws FirebaseMessagingException{
        firebaseService.sendNotification("Notification title", "Notification Text", "Receiver device token");
    } 
    
 
    @RequestMapping("/send-notification")
    @ResponseBody
    public String sendNotification() throws FirebaseMessagingException {
        return firebaseService.sendNotification("prueba","nofunciona","");
    }
}
