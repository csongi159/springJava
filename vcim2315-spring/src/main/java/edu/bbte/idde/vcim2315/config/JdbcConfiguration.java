package edu.bbte.idde.vcim2315.config;

/**
 * JDBC-specifikus konfigurációk bean variánsa
 */
public class JdbcConfiguration {
    // alapértelmezett beállítás adható meg
    // a Jackson fölülírja, ha a JSON állományban meg van adva
    private String driverClass;
    private String url;
    private String username;
    private String password;
    private Integer maxPoolSize;

    public String getDriverClass() {
        return driverClass;
    }

    public void setDriverClass(String driverClass) {
        this.driverClass = driverClass;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getMaxPoolSize() {
        return maxPoolSize;
    }

    public void setMaxPoolSize(Integer maxPoolSize) {
        this.maxPoolSize = maxPoolSize;
    }
}
