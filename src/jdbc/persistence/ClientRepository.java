package jdbc.persistence;
import config.DatabaseConfiguration;
import jdbc.model.Client;
import oracle.jdbc.OraclePreparedStatement;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClientRepository implements GenericRepository<Client> {
    private static ClientRepository instance = null;
    private Connection connection; // Adăugăm conexiunea ca membru al clasei

    public ClientRepository() {
        connection = DatabaseConfiguration.getConnection();
    }

    public static ClientRepository getInstance() {
        if (instance == null) {
            instance = new ClientRepository();
        }
        return instance;
    }

    @Override
    public void add(Client obj) {

        String insertUser = """
                INSERT INTO App_User VALUES (user_sequence.nextval, ?, ?, ?, ?, ?)
                """;
        try (oracle.jdbc.OraclePreparedStatement preparedStatement = (oracle.jdbc.OraclePreparedStatement)
                DatabaseConfiguration.getConnection().prepareStatement(insertUser)) {


            preparedStatement.setString(1, obj.getUsername());
            preparedStatement.setString(2, obj.getEmail());
            preparedStatement.setString(3, obj.getPassword());
            preparedStatement.setString(4, obj.getPhoneNumber());
            preparedStatement.setInt(5, obj.getAge());


            preparedStatement.executeUpdate();

            obj.setUser_id(retrieveLastId("USER_SEQUENCE"));


        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        String insertPod = """
                INSERT INTO client VALUES(client_sequence.nextval, ?, ?)
                """;
        try (oracle.jdbc.OraclePreparedStatement preparedStatement = (oracle.jdbc.OraclePreparedStatement)
                DatabaseConfiguration.getConnection().prepareStatement(insertPod)) {


            preparedStatement.setInt(1, obj.getUser_id());
            preparedStatement.setString(2, obj.getAddress());

            preparedStatement.executeUpdate();
            obj.setClinet_id(retrieveLastId("CLIENT_SEQUENCE"));
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }









    @Override
    public Client get(int id) {
        String selectQuery = """
                SELECT u.user_id, u.username, u.email,
                    u.password, u.age, u.phoneNumber
                        c.client_id, c.address
                FROM App_User u, client c WHERE c.user_id = u.user_id
                AND c.client_id = ?
                """;
        try (oracle.jdbc.OraclePreparedStatement preparedStatement = (oracle.jdbc.OraclePreparedStatement)
                DatabaseConfiguration.getConnection().prepareStatement(selectQuery)) {


            preparedStatement.setInt(1, id);

            ResultSet res = preparedStatement.executeQuery();


            if (res.next()) {
                return new Client(
                        res.getInt(1),
                        res.getString(2),
                        res.getString(3),
                        res.getString(4),
                        res.getString(5),
                        res.getInt(6),
                        res.getInt(7),
                        res.getString(8)
                );
            } else {
                throw new RuntimeException();
            }

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public ArrayList<Client> getAll() {
        String selectQuery = """
        SELECT u.user_id, u.username, u.email,
                u.age, u.phoneNumber, u.password,
                c.client_id, c.address
        FROM App_User u, client c WHERE u.user_id = c.user_id
        """;
        try (oracle.jdbc.OraclePreparedStatement preparedStatement = (oracle.jdbc.OraclePreparedStatement)
                DatabaseConfiguration.getConnection().prepareStatement(selectQuery)) {


            ResultSet res = preparedStatement.executeQuery();

            ArrayList<Client> clients = new ArrayList<>();

            while (res.next()) {
                Client client = new Client(
                        res.getInt(1),
                        res.getString(2),
                        res.getString(3),
                        res.getString(4),
                        res.getString(5),
                        res.getInt(6),
                        res.getInt(7),
                        res.getString(8)
                );
                clients.add(client);
            }
            return clients;
    }catch (SQLException ex){
        throw new RuntimeException(ex);
    }
    }

    @Override
    public void update(Client obj) {
        String updateStatementPod = """
                    UPDATE client
                    SET
                       address = ?
                    WHERE
                        client_id = ?
                """;
        try (oracle.jdbc.OraclePreparedStatement preparedStatement = (oracle.jdbc.OraclePreparedStatement)
                DatabaseConfiguration.getConnection().prepareStatement(updateStatementPod)) {


            preparedStatement.setString(1, obj.getAddress());
            preparedStatement.setInt(2, obj.getClient_id());

            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

        String updateStatementUsr = """
                    UPDATE App_User
                    SET
                        username = ?,
                        password = ?,
                        age = ?
                    WHERE
                        id = ?
                """;
        try (oracle.jdbc.OraclePreparedStatement preparedStatement = (oracle.jdbc.OraclePreparedStatement)
                DatabaseConfiguration.getConnection().prepareStatement(updateStatementUsr)) {


            preparedStatement.setString(1, obj.getUsername());
            preparedStatement.setString(2, obj.getPassword());
            preparedStatement.setInt(3, obj.getAge());
            preparedStatement.setInt(4, obj.getUser_id());

            preparedStatement.executeUpdate();

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

    }

    @Override
    public void delete(Client obj) {
        String deleteStatement = """
                DELETE FROM client
                WHERE client_id = ?
                """;
        try (oracle.jdbc.OraclePreparedStatement preparedStatement = (oracle.jdbc.OraclePreparedStatement)
                DatabaseConfiguration.getConnection().prepareStatement(deleteStatement)) {


            preparedStatement.setInt(1, obj.getClient_id());

            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

        String deleteStatementTrk = """
                DELETE FROM App_User
                WHERE user_id = ?
                """;
        try (oracle.jdbc.OraclePreparedStatement preparedStatement = (OraclePreparedStatement)
                DatabaseConfiguration.getConnection().prepareStatement(deleteStatementTrk)) {


            preparedStatement.setInt(1, obj.getUser_id());

            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

}