package com.bulletinCodeTest.CodeTest.database;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
public class DatabaseConfiguration {

    /***
     * Create the template to connect to the database
     * @param dataSource that is associated with the database
     * @return the template to allow us to access the database
     */
    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    /***
     * Create the SQLite database
     * @return the datasource connected to the database
     */
    @Bean
    @Primary
    @ConfigurationProperties(prefix = "datasource")
    public DataSource dataSource() {
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName("org.sqlite.JDBC");
        dataSourceBuilder.url("jdbc:sqlite:Url.db");
        return dataSourceBuilder.build();
    }
}
