package edu.bbte.idde.vcim2315.data;

import edu.bbte.idde.vcim2315.data.daofactory.DataSourceFactory;
import edu.bbte.idde.vcim2315.exception.ComponentInsertFailedException;
import edu.bbte.idde.vcim2315.exception.ComponentNotFoundException;
import edu.bbte.idde.vcim2315.exception.ComponentQueryFailedException;
import edu.bbte.idde.vcim2315.model.Component;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class JdbcComponentDAO implements ComponentDAO {
    private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(JdbcComponentDAO.class);
    private final transient DataSource  dataSource = DataSourceFactory.getInstance().getComponentDataSource();

    @Override
    public void addComponent(Component component) throws ComponentInsertFailedException {
        String query = "INSERT INTO components (id, name, type, price, quantity) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, component.getId());
            preparedStatement.setString(2, component.getName());
            preparedStatement.setString(3, component.getType());
            preparedStatement.setDouble(4, component.getPrice());
            preparedStatement.setInt(5, component.getQuantity());
            preparedStatement.executeUpdate();
            LOG.info("Component added");
        } catch (SQLException e) {
            LOG.error("Error adding component");
            throw new ComponentInsertFailedException("Component with ID " + component.getId() + " is not inserted.");
        }
    }

    @Override
    public Component getComponentById(long id) throws ComponentNotFoundException, ComponentQueryFailedException {
        String query = "SELECT * FROM components WHERE id = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, id);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return getComponent(resultSet);
                } else {
                    throw new ComponentNotFoundException("Component with ID " + id + " not found.");
                }
            }
        } catch (SQLException e) {
            LOG.error("Error fetching component");
            throw new ComponentQueryFailedException("Component with ID " + id + " is not reachable.");
        }
    }

    private static Component getComponent(ResultSet resultSet) throws SQLException {
        long id = resultSet.getLong("id");
        String name = resultSet.getString("name");
        String type = resultSet.getString("type");
        double price = resultSet.getDouble("price");
        int quantity = resultSet.getInt("quantity");
        LOG.info("Found component");
        return new Component(id, name, type, price, quantity);
    }

    @Override
    public void updateComponent(Component component) throws ComponentNotFoundException, ComponentQueryFailedException {
        String query = "UPDATE components SET name = ?, type = ?, price = ?, quantity = ? WHERE id = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, component.getName());
            preparedStatement.setString(2, component.getType());
            preparedStatement.setDouble(3, component.getPrice());
            preparedStatement.setInt(4, component.getQuantity());
            preparedStatement.setLong(5, component.getId());
            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected == 0) {
                throw new ComponentNotFoundException("Component with ID " + component.getId() + " not found.");
            }
            LOG.info("Component updated:");
        } catch (SQLException e) {
            LOG.error("Error updating component");
            throw new ComponentQueryFailedException("Update failed.");
        }
    }

    @Override
    public void deleteComponent(long id) throws ComponentNotFoundException, ComponentQueryFailedException {
        String query = "DELETE FROM components WHERE id = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, id);
            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected == 0) {
                throw new ComponentNotFoundException("Component with ID " + id + " not found.");
            }
            LOG.info("Component deleted with ID");
        } catch (SQLException e) {
            LOG.error("Error deleting component");
            throw new ComponentQueryFailedException("Delete failed.");
        }
    }

    @Override
    public ConcurrentMap<Long, Component> getAllComponents() throws ComponentQueryFailedException {
        ConcurrentMap<Long, Component> components = new ConcurrentHashMap<>();
        String query = "SELECT * FROM components";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                Component component = getComponent(resultSet);
                components.put(component.getId(), component);
            }
            LOG.info("All components fetched successfully.");
        } catch (SQLException e) {
            LOG.warn("Error fetching all components");
            throw new ComponentQueryFailedException("Getting components has failed.");
        }
        return components;
    }
}
