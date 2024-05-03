package jdbc.persistence;

import jdbc.model.Order;
import oracle.jdbc.OraclePreparedStatement;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderRepository implements GenericRepository<Order> {
    private static OrderRepository instance = null;

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
                INSERT INTO order
                VALUES(order_sequence.nextval,?,?)
                """;
        try{
            PreparedStatement preparedStatement = (OraclePreparedStatement)
                    dbConnection.getContext().prepareStatement(insertStatement);


            preparedStatement.setString(2,obj.getPaymentMethod());
            preparedStatement.setString(3, obj.getDesiredArrivalTime());

            preparedStatement.executeUpdate();

        }catch (SQLException ex){
            throw new RuntimeException(ex);
        }
    }

    @Override
    public Order get(int id) {
        String selectQuery = """
                
                SELECT id, paymentMethod, desiredArrivalTime
                    FROM order 
                    WHERE id = ?
                """;
        try{
            OraclePreparedStatement preparedStatement = (OraclePreparedStatement)
                    dbConnection.getContext().prepareStatement(selectQuery);
            preparedStatement.setInt(1, id);

            ResultSet res = preparedStatement.executeQuery();

            if(res.next()){
                return new Order(
                        new ArrayList<>(),
                        new ArrayList<>(),
                        res.getString(3),
                        res.getString(4)
                );
            }else{
                throw new RuntimeException();
            }
        }catch (SQLException ex){
            throw new RuntimeException(ex);
        }
    }

    @Override
    public ArrayList<Order> getAll() {
        String selectQuery = """
                SELECT paymentMethod,desiredArrivalTime
                    FROM order 
                """;
        try{
            OraclePreparedStatement preparedStatement = (OraclePreparedStatement)
                    dbConnection.getContext().prepareStatement(selectQuery);

            ResultSet res = preparedStatement.executeQuery();

            ArrayList<Order> orders = new ArrayList<>();
            while(res.next()) {
                Order order = new Order(
                        new ArrayList<>(),
                        new ArrayList<>(),
                        res.getString(3),
                        res.getString(4)
                );
                orders.add(order);
            }
            return orders;
        }catch (SQLException ex){
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void update(Order obj) {
        String updateStatement = """
                UPDATE order
                SET
                    paymentMethod = ?,
                    desiredArrivalTime = ?
         
                WHERE
                    id = ?
                """;
        try {
            OraclePreparedStatement preparedStatement = (OraclePreparedStatement)
                    dbConnection.getContext().prepareStatement(updateStatement);

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
                DELETE FROM order
                WHERE id = ?
                """;
        try{
            OraclePreparedStatement preparedStatement = (OraclePreparedStatement)
                    dbConnection.getContext().prepareStatement(deleteStatement);

            preparedStatement.setInt(1, obj.getId());

            preparedStatement.executeUpdate();

        }catch (SQLException ex){
            throw new RuntimeException(ex);
        }
    }
}