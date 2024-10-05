package com.womensafety.Womensafety.Model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "settings")
@Data
public class Settings {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private Boolean notificationEnabled;
    private Boolean darkMode;
    private String languagePreference;

    @OneToOne
    @JoinColumn(name = "users_id")
    private User user;

    public void setUser(User user) {

    }

}
