package jdbc.persistence;


import config.DatabaseConfiguration;
import jdbc.model.Food;
import oracle.jdbc.OraclePreparedStatement;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class FoodRepository implements GenericRepository<Food> {
    private static FoodRepository instance = null;

    public FoodRepository(){}
    public static FoodRepository getInstance(){
        if(instance == null){
            instance = new FoodRepository();
        }
        return instance;
    }

    @Override
    public void add(Food obj) {
        String insertStatement = """
                INSERT INTO food
                VALUES(food_sequence.nextval,?,?,?,?)
                """;

            try (OraclePreparedStatement preparedStatement = (OraclePreparedStatement)
                    DatabaseConfiguration.getConnection().prepareStatement(insertStatement)) {


                preparedStatement.setString(1,obj.getName());
            preparedStatement.setInt(2, obj.getPrice());
            preparedStatement.setInt(3, obj.isVegetarian());
            preparedStatement.setInt(4, obj.getCalories());

            preparedStatement.executeUpdate();

        }catch (SQLException ex){
            throw new RuntimeException(ex);
        }
    }

    @Override
    public Food get(int id) {
        String selectQuery = """
                
                SELECT id, name, price, vegetarian, calories
                    FROM food 
                    WHERE id = ?
                """;

            try (OraclePreparedStatement preparedStatement = (OraclePreparedStatement)
                    DatabaseConfiguration.getConnection().prepareStatement(selectQuery)) {


                preparedStatement.setInt(1, id);

            ResultSet res = preparedStatement.executeQuery();

            if(res.next()){
                return new Food(
                        res.getString(1),
                        res.getInt(2),
                        res.getInt(3),
                        res.getInt(4)

                );
            }else{
                throw new RuntimeException();
            }
        }catch (SQLException ex){
            throw new RuntimeException(ex);
        }
    }

    @Override
    public ArrayList<Food> getAll() {
        String selectQuery = """
                SELECT name, price, vegetarian, calories
                    FROM food 
                """;
        try (OraclePreparedStatement preparedStatement = (OraclePreparedStatement)
                DatabaseConfiguration.getConnection().prepareStatement(selectQuery)) {

            ResultSet res = preparedStatement.executeQuery();

            ArrayList<Food> foods = new ArrayList<>();
            while(res.next()) {
                Food food = new Food(
                        res.getString(1),   // Name
                        res.getInt(2),   // Price
                        res.getInt(3),  // Vegetarian
                        res.getInt(4) // calories
                );
                foods.add(food);
            }
            return foods;
        }catch (SQLException ex){
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void update(Food obj) {
        String updateStatement = """
                UPDATE food
                SET
                    name = ?,
                    price = ?,
                    vegetarian = ?,
                    calories = ?
                WHERE
                    id = ?
                """;

            try (OraclePreparedStatement preparedStatement = (OraclePreparedStatement)
                    DatabaseConfiguration.getConnection().prepareStatement(updateStatement)) {

            preparedStatement.setString(1, obj.getName());
            preparedStatement.setInt(2, obj.getPrice());
            preparedStatement.setInt(3, obj.isVegetarian());
            preparedStatement.setInt(4, obj.getCalories());

            preparedStatement.executeUpdate();

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }


    @Override
    public void delete(Food obj) {
        String deleteStatement = """
                DELETE FROM food
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