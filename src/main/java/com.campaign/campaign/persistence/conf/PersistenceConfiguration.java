package com.campaign.campaign.persistence.conf;

import com.campaign.campaign.conf.ConfigService;
import com.zaxxer.hikari.HikariDataSource;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:/persistence.properties")
public class PersistenceConfiguration {

    @Bean
    public HikariDataSource getDataSource(ConfigService configService) {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl(configService.getString("database.url"));
        dataSource.setUsername(configService.getString("database.user"));
        dataSource.setPassword(configService.getString("database.password"));
        return dataSource;
    }

    @Bean
    public Jdbi jdbi(HikariDataSource hikariDataSource) {
        Jdbi jdbi = Jdbi.create(hikariDataSource);
        jdbi.installPlugin(new SqlObjectPlugin());
        return jdbi;
    }
}
