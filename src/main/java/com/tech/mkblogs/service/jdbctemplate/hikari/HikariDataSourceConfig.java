package com.tech.mkblogs.service.jdbctemplate.hikari;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;

import com.tech.mkblogs.config.DBProperties;

@Configuration
public class HikariDataSourceConfig {

	@Autowired
	private DBProperties dbProperties;
	
    @Bean(name = "hikariDataSource", destroyMethod="")
	@Primary
    public DataSource getHikariDataSource() {
        DataSourceBuilder<?> dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.type(com.zaxxer.hikari.HikariDataSource.class);
        dataSourceBuilder.driverClassName(dbProperties.getDriverClassName());
        dataSourceBuilder.url(dbProperties.getUrl());
        dataSourceBuilder.username(dbProperties.getUsername());
        dataSourceBuilder.password(dbProperties.getPassword());
        return dataSourceBuilder.build();
    }
    
    @Bean(name = "hikariJdbcTemplate")
    @Autowired
    public JdbcTemplate tomcatJdbcTemplate(@Qualifier("hikariDataSource") DataSource ds) {
        return new JdbcTemplate(ds);
    }
}
