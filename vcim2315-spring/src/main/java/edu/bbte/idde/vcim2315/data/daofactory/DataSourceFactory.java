package edu.bbte.idde.vcim2315.data.daofactory;

import javax.sql.DataSource;

public abstract class DataSourceFactory {
    private static DataSourceFactory instance;

    public static synchronized DataSourceFactory getInstance() {
        if (instance == null) {
            instance = new HikariDataSourceFactory();
        }
        return instance;
    }

    public abstract DataSource getComponentDataSource();
}
