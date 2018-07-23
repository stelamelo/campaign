package com.campaign.campaign.conf;

import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service
//@PropertySource("classpath:/stark.properties")
public class BaseConfigService {

    private Environment env;

    public BaseConfigService(Environment env) {
        this.env = env;
    }

    public String getString(String property) {
        String value = env.getProperty(toEnvConvention(property));

        if (value != null) {
            return value;
        }

        return env.getProperty(property);
    }

    private String toEnvConvention(String property) {
        return property.toUpperCase().replace(".", "_");
    }
}
