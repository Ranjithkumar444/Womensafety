package com.womensafety.Womensafety.Controller;

import com.womensafety.Womensafety.Model.Notification;
import com.womensafety.Womensafety.Service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {
    @Autowired
    private NotificationService notificationService;

    @GetMapping("/{usersId}")
    public ResponseEntity<List<Notification>> getUserNotifications(@PathVariable Long usersId) {
        return ResponseEntity.ok(notificationService.getUserNotifications(usersId));
    }
}

