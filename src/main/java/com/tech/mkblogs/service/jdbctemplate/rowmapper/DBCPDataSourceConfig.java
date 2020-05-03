package com.tech.mkblogs.service.jdbctemplate.rowmapper;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import com.tech.mkblogs.config.DBProperties;

@Configuration
public class DBCPDataSourceConfig {

	@Autowired
	private DBProperties dbProperties;
	
	
    @Bean(name = "dbcpDataSource", destroyMethod="")
    public DataSource getDbcpDataSource() {
        DataSourceBuilder<?> dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.type(BasicDataSource.class);
        dataSourceBuilder.driverClassName(dbProperties.getDriverClassName());
        dataSourceBuilder.url(dbProperties.getUrl());
        dataSourceBuilder.username(dbProperties.getUsername());
        dataSourceBuilder.password(dbProperties.getPassword());
        return dataSourceBuilder.build();
    }
	
	
    @Bean(name = "dbcpJdbcTemplate")
    @Autowired
    public JdbcTemplate tomcatJdbcTemplate(@Qualifier("dbcpDataSource") DataSource ds) {
        return new JdbcTemplate(ds);
    }
}
