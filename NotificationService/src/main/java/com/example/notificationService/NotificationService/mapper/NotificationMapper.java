package com.example.notificationService.NotificationService.mapper;

import com.example.notificationService.NotificationService.dto.NotificationRequestDTO;
import com.example.notificationService.NotificationService.dto.NotificationResponseDTO;
import com.example.notificationService.NotificationService.entity.Notification;
import com.example.notificationService.NotificationService.enums.NotificationType;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class NotificationMapper {
    public NotificationResponseDTO toResponseDTO(Notification notification) {
        NotificationResponseDTO notificationResponseDTO = new NotificationResponseDTO();
        notificationResponseDTO.setNotificationId(notification.getNotificationId());
        notificationResponseDTO.setOrderId(notification.getOrderId());
        notificationResponseDTO.setUserId(notification.getUserId());
        notificationResponseDTO.setCreatedAt(notification.getCreatedAt());
        notificationResponseDTO.setContent(notification.getContent());
        notificationResponseDTO.setNotificationType(notification.getNotificationType());

        return notificationResponseDTO;
    }

    public Notification toEntity(NotificationRequestDTO notificationRequestDTO) {
        Notification notification = new Notification();
        notification.setOrderId(notificationRequestDTO.getOrderId());
        notification.setUserId(notificationRequestDTO.getUserId());
        notification.setCreatedAt(LocalDateTime.now());
        notification.setContent(notificationRequestDTO.getNotificationType().name());
        notification.setNotificationType(notificationRequestDTO.getNotificationType());
        return notification;
    }


}
