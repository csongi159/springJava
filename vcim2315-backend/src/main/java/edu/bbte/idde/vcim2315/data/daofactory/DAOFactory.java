package edu.bbte.idde.vcim2315.data.daofactory;

import edu.bbte.idde.vcim2315.config.ConfigurationFactory;
import edu.bbte.idde.vcim2315.data.ComponentDAO;

import java.util.Objects;

public abstract class DAOFactory {
    private static DAOFactory instance;

    public static synchronized DAOFactory getInstance() {
        if (instance == null) {
            var daoConfig = ConfigurationFactory.getDaoConfiguration();
            if (Objects.equals(daoConfig.getDaoType(), "jdbc")) {
                instance = new JdbcComponentDAOFactory();
            } else {
                instance = new InMemoryComponentDAOFactory();
            }
        }
        return instance;
    }

    public abstract ComponentDAO getComponentDAO();
}
