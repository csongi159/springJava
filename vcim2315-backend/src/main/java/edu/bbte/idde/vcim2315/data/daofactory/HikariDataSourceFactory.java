package edu.bbte.idde.vcim2315.data.daofactory;

import com.zaxxer.hikari.HikariDataSource;
import edu.bbte.idde.vcim2315.config.ConfigurationFactory;

import javax.sql.DataSource;

public class HikariDataSourceFactory extends DataSourceFactory {
    private HikariDataSource dataSource;

    @Override
    public synchronized DataSource getComponentDataSource() {
        if (dataSource == null) {
            dataSource = new HikariDataSource();
            var jdbcConfiguration = ConfigurationFactory.getJdbcConfiguration();
            dataSource.setDriverClassName(jdbcConfiguration.getDriverClass());
            dataSource.setJdbcUrl(jdbcConfiguration.getUrl());
            dataSource.setUsername(jdbcConfiguration.getUsername());
            dataSource.setPassword(jdbcConfiguration.getPassword());
            dataSource.setMaximumPoolSize(jdbcConfiguration.getMaxPoolSize());
        }
        return dataSource;
    }
}
