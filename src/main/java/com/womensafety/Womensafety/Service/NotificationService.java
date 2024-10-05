package com.womensafety.Womensafety.Service;

import com.womensafety.Womensafety.Model.Notification;
import com.womensafety.Womensafety.Repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationService {
    @Autowired
    private NotificationRepository notificationRepository;

    public List<Notification> getUserNotifications(Long usersId) {
        return notificationRepository.findAllByUserId(usersId);
    }
}
