package com.womensafety.Womensafety.Service;

import com.womensafety.Womensafety.Model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;

public interface CustomUserDetailsService extends UserDetailsService {
    Optional<User> findByUsername(String username);

}
