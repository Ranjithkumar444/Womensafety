package com.womensafety.Womensafety.Model;

import jakarta.persistence.*;
import lombok.Data;

import java.security.Timestamp;

@Entity
@Table(name = "notifications")
@Data
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String notificationTitle;
    private String notificationBody;
    private Boolean isRead;
    private Timestamp createdAt;

    @ManyToOne
    @JoinColumn(name = "users_id")
    private User user;

}
