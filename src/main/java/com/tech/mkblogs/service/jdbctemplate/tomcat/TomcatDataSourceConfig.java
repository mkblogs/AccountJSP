package com.tech.mkblogs.service.jdbctemplate.tomcat;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import com.tech.mkblogs.config.DBProperties;

@Configuration
public class TomcatDataSourceConfig {

	@Autowired
	private DBProperties dbProperties;
	
	
    @Bean(name = "tomcatDataSource", destroyMethod="")
    public DataSource getTomcatDataSource() {
        DataSourceBuilder<?> dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.type(org.apache.tomcat.jdbc.pool.DataSource.class);
        dataSourceBuilder.driverClassName(dbProperties.getDriverClassName());
        dataSourceBuilder.url(dbProperties.getUrl());
        dataSourceBuilder.username(dbProperties.getUsername());
        dataSourceBuilder.password(dbProperties.getPassword());
        return dataSourceBuilder.build();
    }
	
	
    @Bean(name = "tomcatJdbcTemplate")
    @Autowired
    public JdbcTemplate tomcatJdbcTemplate(@Qualifier("tomcatDataSource") DataSource ds) {
        return new JdbcTemplate(ds);
    }
}
