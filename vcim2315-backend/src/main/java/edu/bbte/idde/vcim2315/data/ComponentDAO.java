package edu.bbte.idde.vcim2315.data;

import edu.bbte.idde.vcim2315.exception.ComponentInsertFailedException;
import edu.bbte.idde.vcim2315.exception.ComponentNotFoundException;
import edu.bbte.idde.vcim2315.exception.ComponentQueryFailedException;
import edu.bbte.idde.vcim2315.model.Component;

import java.util.concurrent.ConcurrentMap;

public interface ComponentDAO {
    void addComponent(Component component) throws ComponentInsertFailedException;


    Component getComponentById(long id) throws ComponentNotFoundException, ComponentQueryFailedException;

    void updateComponent(Component component) throws ComponentNotFoundException, ComponentQueryFailedException;

    void deleteComponent(long id) throws ComponentNotFoundException, ComponentQueryFailedException;

    ConcurrentMap<Long, Component> getAllComponents() throws ComponentQueryFailedException;
}
