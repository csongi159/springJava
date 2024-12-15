package edu.bbte.idde.vcim2315.service;

import edu.bbte.idde.vcim2315.data.ComponentDAO;
import edu.bbte.idde.vcim2315.data.daofactory.DAOFactory;
import edu.bbte.idde.vcim2315.exception.ComponentInsertFailedException;
import edu.bbte.idde.vcim2315.exception.ComponentNotFoundException;
import edu.bbte.idde.vcim2315.exception.ComponentQueryFailedException;
import edu.bbte.idde.vcim2315.model.Component;

import java.util.Map;

public class ComponentService implements Service {

    private final transient ComponentDAO componentDAO;

    public ComponentService() {
        this.componentDAO = DAOFactory.getInstance().getComponentDAO();
    }

    @Override
    public void addComponent(Component component) throws ComponentInsertFailedException {
        componentDAO.addComponent(component);
    }

    @Override
    public Component getComponent(long id) throws ComponentNotFoundException, ComponentQueryFailedException {
        return componentDAO.getComponentById(id);
    }

    @Override
    public void updateComponent(Component component) throws ComponentNotFoundException, ComponentQueryFailedException {
        componentDAO.updateComponent(component);
    }

    @Override
    public void deleteComponent(long id) throws ComponentNotFoundException, ComponentQueryFailedException {
        componentDAO.deleteComponent(id);
    }

    @Override
    public Map<Long, Component> getAllComponents() throws ComponentQueryFailedException {
        return componentDAO.getAllComponents();
    }
}
