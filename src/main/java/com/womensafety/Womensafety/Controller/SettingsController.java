package com.womensafety.Womensafety.Controller;

import com.womensafety.Womensafety.Model.Settings;
import com.womensafety.Womensafety.Model.User;
import com.womensafety.Womensafety.Service.SettingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/settings")
public class SettingsController {
    @Autowired
    private SettingsService settingsService;

    @GetMapping("/{usersId}")
    public ResponseEntity<Settings> getUserSettings(@PathVariable Long usersId) {
        return ResponseEntity.ok(settingsService.getUserSettings(usersId));
    }

    @PostMapping("/{usersId}")
    public ResponseEntity<Settings> updateSettings(@PathVariable Long usersId, @RequestBody Settings settings) {
        settings.setUser(new User(usersId));
        return ResponseEntity.ok(settingsService.saveSettings(settings));
    }
}

