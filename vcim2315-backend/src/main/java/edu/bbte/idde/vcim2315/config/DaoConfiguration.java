package edu.bbte.idde.vcim2315.config;

public class DaoConfiguration {
    private String daoType = "inmemory";

    public String getDaoType() {
        return daoType;
    }

    public void setDaoType(String daoType) {
        this.daoType = daoType;
    }
}
