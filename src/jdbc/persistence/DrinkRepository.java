package jdbc.persistence;


import config.DatabaseConfiguration;
import jdbc.model.Drink;
import jdbc.model.Food;
import oracle.jdbc.OraclePreparedStatement;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DrinkRepository implements GenericRepository<Drink> {
    private static DrinkRepository instance = null;

    public DrinkRepository(){}
    public static DrinkRepository getInstance(){
        if(instance == null){
            instance = new DrinkRepository();
        }
        return instance;
    }

    @Override
    public void add(Drink obj) {
        String insertStatement = """
                INSERT INTO drink
                VALUES(drink_sequence.nextval,?,?,?,?)
                """;

            try (OraclePreparedStatement preparedStatement = (OraclePreparedStatement)
                    DatabaseConfiguration.getConnection().prepareStatement(insertStatement)) {


                preparedStatement.setString(1,obj.getName());
            preparedStatement.setInt(2, obj.getPrice());
            preparedStatement.setInt(3, obj.isAlcoholic());
            preparedStatement.setInt(4, obj.getCalories());

            preparedStatement.executeUpdate();

        }catch (SQLException ex){
            throw new RuntimeException(ex);
        }
    }

    @Override
    public Drink get(int id) {
        String selectQuery = """
                
                SELECT name, price, alcholic, calories
                    FROM drink 
                    WHERE id = ?
                """;

            try (OraclePreparedStatement preparedStatement = (OraclePreparedStatement)
                    DatabaseConfiguration.getConnection().prepareStatement(selectQuery)) {

                preparedStatement.setInt(1, id);

            ResultSet res = preparedStatement.executeQuery();

            if(res.next()){
//                return new Drink(
//
//                        res.getString(1),
//                        res.getInt(2),
//                        res.getInt(3),
//                        res.getInt(4)
//                );


                String name = res.getString("name");
                int price = res.getInt("price");
                int alcholic = res.getInt("alcholic");
                int calories = res.getInt("calories");
                return new Drink(name, price, alcholic, calories);

            }else{
                throw new RuntimeException();
            }
        }catch (SQLException ex){
            throw new RuntimeException(ex);
        }
    }

    @Override
    public ArrayList<Drink> getAll() {
        String selectQuery = """
                SELECT  name, price, alcoholic, calories
                    FROM drink 
                """;
        try (OraclePreparedStatement preparedStatement = (OraclePreparedStatement)
                DatabaseConfiguration.getConnection().prepareStatement(selectQuery)) {

            ResultSet res = preparedStatement.executeQuery();

            ArrayList<Drink> drinks = new ArrayList<>();
            while(res.next()) {
                Drink dr = new Drink(
                        res.getString(1),
                        res.getInt(2),
                        res.getInt(3),
                        res.getInt(4)
                );
                drinks.add(dr);
            }
            return drinks;
        }catch (SQLException ex){
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void update(Drink obj) {
        String updateStatement = """
                UPDATE drink
                SET
                    name = ?,
                    price = ?,
                    alchoolic = ?,
                    calories = ?
                WHERE
                    id = ?
                """;
        try (OraclePreparedStatement preparedStatement = (OraclePreparedStatement)
                DatabaseConfiguration.getConnection().prepareStatement(updateStatement)) {


            preparedStatement.setString(1, obj.getName());
            preparedStatement.setDouble(2, obj.getPrice());
            preparedStatement.setInt(3, obj.isAlcoholic());
            preparedStatement.setInt(4, obj.getCalories());

            preparedStatement.executeUpdate();

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void delete(Drink obj) {
        String deleteStatement = """
                DELETE FROM drink
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