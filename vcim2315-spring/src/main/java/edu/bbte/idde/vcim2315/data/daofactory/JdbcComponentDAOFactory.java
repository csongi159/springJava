package edu.bbte.idde.vcim2315.data.daofactory;

import edu.bbte.idde.vcim2315.data.ComponentDAO;
import edu.bbte.idde.vcim2315.data.JdbcComponentDAO;

public class JdbcComponentDAOFactory  extends DAOFactory {
    private JdbcComponentDAO jdbcComponentDAO;

    @Override
    public synchronized ComponentDAO getComponentDAO() {
        if (jdbcComponentDAO == null) {
            jdbcComponentDAO = new JdbcComponentDAO();
        }
        return jdbcComponentDAO;
    }
}
