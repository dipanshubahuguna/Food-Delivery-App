package com.example.notificationService.NotificationService.dto;

import com.example.notificationService.NotificationService.enums.NotificationType;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NotificationResponseDTO {
    private Long notificationId;
    private Long userId;
    private Long orderId;
    private NotificationType notificationType;
    private LocalDateTime createdAt;
    private String content;
}
