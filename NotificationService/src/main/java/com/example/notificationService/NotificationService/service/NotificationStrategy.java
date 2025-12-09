package com.example.notificationService.NotificationService.service;

import com.example.notificationService.NotificationService.dto.NotificationRequestDTO;
import org.springframework.stereotype.Service;


public interface NotificationStrategy {
    public void notify(NotificationRequestDTO notificationRequestDTO);
}
