package com.example.notificationService.NotificationService.controller;

import com.example.notificationService.NotificationService.dto.NotificationRequestDTO;
import com.example.notificationService.NotificationService.dto.NotificationResponseDTO;
import com.example.notificationService.NotificationService.service.NotificationService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/notification")
@AllArgsConstructor
public class NotificationController {
    private final NotificationService notificationService;

    @PostMapping(path = "/notify")
    public ResponseEntity<NotificationResponseDTO> initiateNotification(
            @Valid @RequestBody NotificationRequestDTO notificationRequestDTO ) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(notificationService.sendNotification(notificationRequestDTO));
    }

}
