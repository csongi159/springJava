package edu.bbte.idde.vcim2315.service;

import edu.bbte.idde.vcim2315.exception.ComponentInsertFailedException;
import edu.bbte.idde.vcim2315.exception.ComponentNotFoundException;
import edu.bbte.idde.vcim2315.exception.ComponentQueryFailedException;
import edu.bbte.idde.vcim2315.model.Component;

import java.util.Map;

public interface Service {

    void addComponent(Component component) throws ComponentInsertFailedException;

    Component getComponent(long id) throws ComponentNotFoundException, ComponentQueryFailedException;

    void updateComponent(Component component) throws ComponentNotFoundException, ComponentQueryFailedException;

    void deleteComponent(long id) throws ComponentNotFoundException, ComponentQueryFailedException;

    Map<Long, Component> getAllComponents() throws ComponentQueryFailedException;
}
