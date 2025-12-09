package com.example.notificationService.NotificationService.entity;

import com.example.notificationService.NotificationService.enums.NotificationType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "notification")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notification_id")
    private Long notificationId;
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "order_id")
    private Long orderId;
    @Column(name = "type")
    private NotificationType notificationType;
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @Column(name = "content")
    private String content;
}
