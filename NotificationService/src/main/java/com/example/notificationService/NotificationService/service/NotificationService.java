package com.example.notificationService.NotificationService.service;

import com.example.notificationService.NotificationService.dto.NotificationRequestDTO;
import com.example.notificationService.NotificationService.dto.NotificationResponseDTO;
import com.example.notificationService.NotificationService.entity.Notification;
import com.example.notificationService.NotificationService.enums.NotificationType;
import com.example.notificationService.NotificationService.mapper.NotificationMapper;
import com.example.notificationService.NotificationService.repository.NotificationRepository;
import lombok.AllArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;

@Service
public class NotificationService {
    private final NotificationRepository notificationRepository;
    private final Map<String,NotificationStrategy> notificationStrategyMap;
    private final NotificationMapper notificationMapper;

    public NotificationService(NotificationRepository notificationRepository,
                               Map<String, NotificationStrategy> notificationStrategyMap,
                               NotificationMapper notificationMapper) {
        this.notificationRepository = notificationRepository;
        this.notificationStrategyMap = notificationStrategyMap;
        this.notificationMapper = notificationMapper;
    }


    public NotificationResponseDTO sendNotification(NotificationRequestDTO notificationRequestDTO) {
        NotificationStrategy notificationStrategy = notificationStrategyMap
                .get(notificationRequestDTO.getNotificationType().name());

        notificationStrategy.notify(notificationRequestDTO);

        Notification notification = notificationMapper.toEntity(notificationRequestDTO);
        Notification savedNotification = notificationRepository.save(notification);

        return notificationMapper.toResponseDTO(savedNotification);
    }


}
