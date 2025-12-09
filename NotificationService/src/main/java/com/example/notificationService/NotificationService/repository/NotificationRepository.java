package com.example.notificationService.NotificationService.repository;

import com.example.notificationService.NotificationService.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NotificationRepository extends JpaRepository<Notification,Long> {
    Optional<Notification> findByOrderId(Long orderId);
}
