package edu.bbte.idde.vcim2315.data;

import edu.bbte.idde.vcim2315.exception.ComponentInsertFailedException;
import edu.bbte.idde.vcim2315.exception.ComponentNotFoundException;
import edu.bbte.idde.vcim2315.model.Component;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class InMemoryComponentDAO implements ComponentDAO {
    private final transient ConcurrentMap<Long, Component> components = new ConcurrentHashMap<>();

    @Override
    public void addComponent(Component component) throws ComponentInsertFailedException {
        if (components.containsKey(component.getId())) {
            throw new ComponentInsertFailedException("Component with ID " + component.getId() + " is already exists.");
        }
        components.put(component.getId(), component);
    }

    @Override
    public Component getComponentById(long id) throws ComponentNotFoundException {
        if (!components.containsKey(id)) {
            throw new ComponentNotFoundException("Component with ID " + id + " not found.");
        }
        return components.get(id);
    }

    @Override
    public void updateComponent(Component component) throws ComponentNotFoundException {
        if (!components.containsKey(component.getId())) {
            throw new ComponentNotFoundException("Component with ID " + component.getId() + " not found.");
        }
        components.put(component.getId(), component);
    }

    @Override
    public void deleteComponent(long id) throws ComponentNotFoundException {
        if (!components.containsKey(id)) {
            throw new ComponentNotFoundException("Component with ID " + id + " not found.");
        }
        components.remove(id);
    }

    @Override
    public ConcurrentMap<Long, Component> getAllComponents() {
        return components;
    }
}
