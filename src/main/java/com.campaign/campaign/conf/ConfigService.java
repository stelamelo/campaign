package com.campaign.campaign.conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

@Service
@PropertySource("classpath:/application.properties")
public class ConfigService {
    
    @Autowired
    private BaseConfigService baseConfigService;
    
    public String getString(String property) {
        return baseConfigService.getString(property);
    }
}
