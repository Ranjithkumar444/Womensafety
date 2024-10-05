package com.womensafety.Womensafety.Model;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "account_info")
@Data
public class AccountInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long usersId;
    private String address;
    private String city;
    private String state;
    private String country;
    private String postalCode;

    @OneToOne
    @JoinColumn(name = "users_id")
    private User user;

    public void setUser(User user) {
    }

}
