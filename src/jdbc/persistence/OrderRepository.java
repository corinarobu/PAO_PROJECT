package jdbc.persistence;

import config.DatabaseConfiguration;
import jdbc.model.Drink;
import jdbc.model.Food;
import jdbc.model.Order;
import oracle.jdbc.OraclePreparedStatement;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderRepository implements GenericRepository<Order> {
    private static OrderRepository instance = null;
    private DrinkRepository drinkRepository = DrinkRepository.getInstance();
    private FoodRepository foodRepository = FoodRepository.getInstance();

    private final List<Order> orders = new ArrayList<>();


    public OrderRepository(){}
    public static OrderRepository getInstance(){
        if(instance == null){
            instance = new OrderRepository();
        }
        return instance;
    }

    @Override
    public void add(Order obj) {
        String insertStatement = """
                INSERT INTO orders
                VALUES (order_sequence.nextval,?, ?, ?, ?)
                """;
        try (OraclePreparedStatement preparedStatement = (OraclePreparedStatement)
                DatabaseConfiguration.getConnection().prepareStatement(insertStatement)) {


            preparedStatement.setString(1, obj.getPaymentMethod());
            preparedStatement.setString(2, obj.getDesiredArrivalTime());
            preparedStatement.setInt(3, obj.getFoodItem().getId());
            preparedStatement.setInt(4, obj.getDrinkItem().getId());

            preparedStatement.executeUpdate();
            orders.add(obj);

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public Order get(int id) {
        String selectQuery = """
                SELECT paymentMethod, desiredArrivalTime, foodItemId, drinkItemId
                FROM orders 
                WHERE id = ?
                """;
        try (OraclePreparedStatement preparedStatement = (OraclePreparedStatement)
                DatabaseConfiguration.getConnection().prepareStatement(selectQuery)) {

            preparedStatement.setInt(1, id);

            ResultSet res = preparedStatement.executeQuery();

            if (res.next()) {
                Food food = foodRepository.get(res.getInt("foodItemId"));
                Drink drink = drinkRepository.get(res.getInt("drinkItemId"));
                return new Order(
                        res.getString(1),
                        res.getString(2),
                        food,
                        drink
                );
            } else {
                throw new RuntimeException();
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public ArrayList<Order> getAll() {
        String selectQuery = """
                SELECT paymentMethod, desiredArrivalTime, foodItemId, drinkItemId
                FROM orders 
                """;
        try (OraclePreparedStatement preparedStatement = (OraclePreparedStatement)
                DatabaseConfiguration.getConnection().prepareStatement(selectQuery)) {


            ResultSet res = preparedStatement.executeQuery();

            ArrayList<Order> orders = new ArrayList<>();
            while (res.next()) {
                Food food = foodRepository.get(res.getInt("foodItemId"));
                Drink drink = drinkRepository.get(res.getInt("drinkItemId"));
                Order order = new Order(
                        res.getString(1),
                        res.getString(2),
                        food,
                        drink
                );
                orders.add(order);
            }
            return orders;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }


    @Override
    public void update(Order obj) {
        String updateStatement = """
                UPDATE orders
                SET
                    paymentMethod = ?,
                    desiredArrivalTime = ?
         
                WHERE
                    id = ?
                """;
        try (OraclePreparedStatement preparedStatement = (OraclePreparedStatement)
                DatabaseConfiguration.getConnection().prepareStatement(updateStatement)) {


            preparedStatement.setString(1, obj.getPaymentMethod());
            preparedStatement.setString(2, obj.getDesiredArrivalTime());

            preparedStatement.executeUpdate();

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void delete(Order obj) {
        String deleteStatement = """
            DELETE FROM orders
            WHERE id = ?
            """;
        try (OraclePreparedStatement preparedStatement = (OraclePreparedStatement)
                DatabaseConfiguration.getConnection().prepareStatement(deleteStatement)) {

            preparedStatement.setInt(1, obj.getId());
            int deletedRows = preparedStatement.executeUpdate();

            if (deletedRows == 1) {
                orders.remove(obj); // Șterge comanda din lista locală
                System.out.println("Order deleted successfully.");
            } else {
                throw new RuntimeException("Failed to delete order.");
            }

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }





    public Order getNextOrder() {
        if (!orders.isEmpty()) {
            return orders.get(0);
        } else {
            return null;
        }
    }
}