package edu.bbte.idde.vcim2315.config;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MainConfiguration {
    @JsonProperty("jdbc")
    private JdbcConfiguration jdbcConfiguration = new JdbcConfiguration();

    @JsonProperty("dao")
    private DaoConfiguration daoConfiguration = new DaoConfiguration();

    public JdbcConfiguration getJdbcConfiguration() {
        return jdbcConfiguration;
    }

    public void setJdbcConfiguration(JdbcConfiguration jdbcConfiguration) {
        this.jdbcConfiguration = jdbcConfiguration;
    }

    public DaoConfiguration getDaoConfiguration() {
        return daoConfiguration;
    }

    public void setDaoConfiguration(DaoConfiguration daoConfiguration) {
        this.daoConfiguration = daoConfiguration;
    }

    @Override
    public String toString() {
        return "MainConfiguration{" + "jdbcConfiguration=" + jdbcConfiguration + ", daoConfiguration="
                + daoConfiguration + '}';
    }
}
