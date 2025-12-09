package com.example.notificationService.NotificationService.service;

import com.example.notificationService.NotificationService.dto.NotificationRequestDTO;
import org.springframework.stereotype.Service;

@Service("EMAIL")
public class EmailNotificationStrategy implements NotificationStrategy{

    @Override
    public void notify(NotificationRequestDTO notificationRequestDTO) {
        System.out.println("Notifying user :" + notificationRequestDTO.getUserId() +
                " for order : " + notificationRequestDTO.getOrderId() +
                " with : " + notificationRequestDTO.getNotificationType());
    }
}
