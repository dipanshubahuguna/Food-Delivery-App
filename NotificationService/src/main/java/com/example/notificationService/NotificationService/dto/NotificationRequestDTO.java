package com.example.notificationService.NotificationService.dto;

import com.example.notificationService.NotificationService.enums.NotificationType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
public class NotificationRequestDTO {
    @NotNull @Positive
    private Long userId;
    @NotNull @Positive
    private Long orderId;
    @NotNull
    private NotificationType notificationType;
}
