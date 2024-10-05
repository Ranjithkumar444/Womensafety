package com.womensafety.Womensafety.Service;

import com.womensafety.Womensafety.Model.Settings;
import com.womensafety.Womensafety.Repository.SettingsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SettingsService {
    @Autowired
    private SettingsRepository settingsRepository;

    public Settings getUserSettings(Long usersId) {
        return settingsRepository.findByUserId(usersId).orElse(null);
    }

    public Settings saveSettings(Settings settings) {
        return settingsRepository.save(settings);
    }
}
