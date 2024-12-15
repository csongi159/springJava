package edu.bbte.idde.vcim2315.data.daofactory;

import edu.bbte.idde.vcim2315.data.ComponentDAO;
import edu.bbte.idde.vcim2315.data.InMemoryComponentDAO;

public class InMemoryComponentDAOFactory extends DAOFactory {
    private InMemoryComponentDAO inMemoryComponentDAO;

    @Override
    public synchronized ComponentDAO getComponentDAO() {
        if (inMemoryComponentDAO == null) {
            inMemoryComponentDAO = new InMemoryComponentDAO();
        }
        return inMemoryComponentDAO;
    }
}
