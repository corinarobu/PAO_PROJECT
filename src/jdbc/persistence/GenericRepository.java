package jdbc.persistence;
import config.DatabaseConfiguration;
import jdbc.exceptions.InvalidDataException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface GenericRepository<T> {
    DatabaseConfiguration dbConnection = DatabaseConfiguration.getInstance();

    public void add(T entity);
    public T get(int id);
    public ArrayList<T> getAll() throws RuntimeException, InvalidDataException;
    public void update(T entity);
    public void delete(T entity);

      default int retrieveLastId(String index) {
        String selectQuery = "SELECT last_number FROM user_sequences WHERE SEQUENCE_NAME = ?";
        int result = -1; // Valoarea implicită pentru cazurile în care secvența nu este găsită sau este nulă

        try (PreparedStatement preparedStatement = dbConnection.getContext().prepareStatement(selectQuery)) {
            preparedStatement.setString(1, index);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    String lastNumber = resultSet.getString("last_number");
                    if (lastNumber != null) {
                        result = Integer.parseInt(lastNumber) - 1;
                    }
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return result;
    }

}