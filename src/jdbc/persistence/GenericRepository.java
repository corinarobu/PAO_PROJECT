package jdbc.persistence;

import config.DatabaseConfiguration;
import jdbc.exceptions.InvalidDataException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface GenericRepository<T> {

    void add(T entity);
    T get(int id);
    ArrayList<T> getAll() throws RuntimeException, InvalidDataException;
    void update(T entity);
    void delete(T entity);

    default int retrieveLastId(String index) {
        String selectQuery = "SELECT last_number FROM user_sequences WHERE SEQUENCE_NAME = ?";
        int result = -1;

        try (PreparedStatement preparedStatement = DatabaseConfiguration.getConnection().prepareStatement(selectQuery)) {
            preparedStatement.setString(1, index);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    result = resultSet.getInt("last_number") - 1;
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return result;
    }
}
