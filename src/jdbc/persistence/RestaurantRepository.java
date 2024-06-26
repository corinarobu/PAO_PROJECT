package jdbc.persistence;

import config.DatabaseConfiguration;
import jdbc.model.Order;
import jdbc.model.Restaurant;
import oracle.jdbc.OraclePreparedStatement;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RestaurantRepository implements GenericRepository<Restaurant> {
    private static RestaurantRepository instance = null;

    public RestaurantRepository(){}
    public static RestaurantRepository getInstance(){
        if(instance == null){
            instance = new RestaurantRepository();
        }
        return instance;
    }

    @Override
    public void add(Restaurant obj) {
        String insertStatement = """
            INSERT INTO restaurant
            VALUES(restaurant_sequence.nextval, ?, ?, ?, ?, ?)
            """;
        try (OraclePreparedStatement preparedStatement = (OraclePreparedStatement)
                DatabaseConfiguration.getConnection().prepareStatement(insertStatement)) {

            preparedStatement.setString(1, obj.getName());
            preparedStatement.setString(2, obj.getAddress());
            preparedStatement.setInt(3, obj.getEstablishmentYear());
            preparedStatement.setString(4, obj.getCuisineType());
            preparedStatement.setString(5, obj.getOpeningHours());

            preparedStatement.executeUpdate();

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }


    @Override
    public Restaurant get(int id) {
        String selectQuery = """
               
                SELECT id, name, address, establishmentYear ,  cuisineType, openingHours
                    FROM restaurant 
                    WHERE id = ?
                """;
        try (OraclePreparedStatement preparedStatement = (OraclePreparedStatement)
                DatabaseConfiguration.getConnection().prepareStatement(selectQuery)) {

            preparedStatement.setInt(1, id);

            ResultSet res = preparedStatement.executeQuery();

            if(res.next()){
                return new Restaurant(
                        res.getString(1),
                        res.getString(2),
                        res.getInt(3),
                        res.getString(4),
                        res.getString(5)
                );
            }else{
                throw new RuntimeException();
            }
        }catch (SQLException ex){
            throw new RuntimeException(ex);
        }
    }


    @Override
    public ArrayList<Restaurant> getAll() {
        String selectQuery = """
            SELECT name, address, establishmentYear, cuisineType, openingHours
            FROM restaurant
        """;
        try (OraclePreparedStatement preparedStatement = (OraclePreparedStatement)
                DatabaseConfiguration.getConnection().prepareStatement(selectQuery)) {

            ResultSet res = preparedStatement.executeQuery();

            ArrayList<Restaurant> restaurants = new ArrayList<>();
            while (res.next()) {
                Restaurant restaurant = new Restaurant(
                        res.getString(1),
                        res.getString(2),
                        res.getInt(3),
                        res.getString(4),
                        res.getString(5)
                );
                restaurants.add(restaurant);
            }
            return restaurants;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }



    @Override
    public void update(Restaurant obj) {
        String updateStatement = """
            UPDATE restaurant
            SET
                name = ?,
                address = ?, 
                establishmentYear = ?,
                cuisineType = ?,  
                openingHours = ?
         
            WHERE
                id = ?
            """;
        try (OraclePreparedStatement preparedStatement = (OraclePreparedStatement)
                DatabaseConfiguration.getConnection().prepareStatement(updateStatement)) {

            preparedStatement.setString(1, obj.getName());
            preparedStatement.setString(2, obj.getAddress());
            preparedStatement.setInt(3, obj.getEstablishmentYear());
            preparedStatement.setString(4, obj.getCuisineType());
            preparedStatement.setString(5, obj.getOpeningHours());
            preparedStatement.setInt(6, obj.getId());

            preparedStatement.executeUpdate();

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }


    @Override
    public void delete(Restaurant obj) {
        String deleteStatement = """
                DELETE FROM restaurant
                WHERE id = ?
                """;
        try (OraclePreparedStatement preparedStatement = (OraclePreparedStatement)
                DatabaseConfiguration.getConnection().prepareStatement(deleteStatement)) {

            preparedStatement.setInt(1, obj.getId());

            preparedStatement.executeUpdate();

        }catch (SQLException ex){
            throw new RuntimeException(ex);
        }
    }
}