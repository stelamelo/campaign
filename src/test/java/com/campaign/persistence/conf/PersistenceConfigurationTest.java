package com.campaign.persistence.conf;

import com.zaxxer.hikari.HikariDataSource;
import org.jdbi.v3.core.Jdbi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.InputStream;

@Configuration
public class PersistenceConfigurationTest {

    protected Jdbi jdbi;
    private HikariDataSource dataSource;

    @Bean
    public HikariDataSource getDataSource() {
        dataSource = new HikariDataSource();
        dataSource.setJdbcUrl("jdbc:h2:mem");
        dataSource.setUsername("sa");
        dataSource.setPassword("");
        return dataSource;
    }

    @Bean
    public Jdbi jdbi() {
        jdbi = Jdbi.create(dataSource);
        jdbi.withHandle(handle -> {
            InputStream resourceStream = this.getClass().getResourceAsStream("/queries.sql");
            return handle.execute(String.valueOf(resourceStream));
        });
        return jdbi;
    }
}
