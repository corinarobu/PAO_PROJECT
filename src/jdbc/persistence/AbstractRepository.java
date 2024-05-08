package jdbc.persistence;

import config.DatabaseConfiguration;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class AbstractRepository<T> implements GenericRepository<T> {
    protected Connection connection;

    public AbstractRepository() {
        this.connection = DatabaseConfiguration.getConnection();
    }

    protected int retrieveLastId(String sequenceName) {
        String query = "SELECT " + sequenceName + ".CURRVAL FROM dual";

        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            if (resultSet.next()) {
                return resultSet.getInt(1);
            } else {
                throw new SQLException("Failed to retrieve last ID from sequence.");
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Error retrieving last ID from sequence: " + ex.getMessage());
        }
    }
}
