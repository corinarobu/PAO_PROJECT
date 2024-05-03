package jdbc.persistence;

import jdbc.model.Review;
import oracle.jdbc.OraclePreparedStatement;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ReviewRepository implements GenericRepository<Review>{
    private static ReviewRepository instance = null;

    public ReviewRepository(){}
    public static ReviewRepository getInstance(){
        if(instance == null){
            instance = new ReviewRepository();
        }
        return instance;
    }

    @Override
    public void add(Review obj) {
        String insertStatement = """
                INSERT INTO review
                VALUES(order_sequence.nextval,?,?,?)
                """;
        try{
            PreparedStatement preparedStatement = (OraclePreparedStatement)
                    dbConnection.getContext().prepareStatement(insertStatement);


            preparedStatement.setInt(3, obj.getRating());
            preparedStatement.setString(3, obj.getComment());
            preparedStatement.setInt(3, obj.getUserAge());

            preparedStatement.executeUpdate();

        }catch (SQLException ex){
            throw new RuntimeException(ex);
        }
    }

    @Override
    public Review get(int id) {
        String selectQuery = """
               
                SELECT  rating, comment, userAge
                    FROM review 
                    WHERE id = ?
                """;
        try{
            OraclePreparedStatement preparedStatement = (OraclePreparedStatement)
                    dbConnection.getContext().prepareStatement(selectQuery);
            preparedStatement.setInt(1, id);

            ResultSet res = preparedStatement.executeQuery();

            if(res.next()){
                return new Review(
                        res.getInt(1),
                        res.getString(2),
                        res.getInt(3)
                );
            }else{
                throw new RuntimeException();
            }
        }catch (SQLException ex){
            throw new RuntimeException(ex);
        }
    }

    @Override
    public ArrayList<Review> getAll() {
        String selectQuery = """
                    SELECT rating, comment, userAge 
                    FROM review 
                """;
        try{
            OraclePreparedStatement preparedStatement = (OraclePreparedStatement)
                    dbConnection.getContext().prepareStatement(selectQuery);

            ResultSet res = preparedStatement.executeQuery();

            ArrayList<Review> reviews = new ArrayList<>();
            while(res.next()) {
                Review review = new Review(
                        res.getInt(1),
                        res.getString(2),
                        res.getInt(3)

                );
                reviews.add(review);
            }
            return reviews;
        }catch (SQLException ex){
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void update(Review obj) {
        String updateStatement = """
                UPDATE review
                SET
                    rating = ?,
                    comment = ?, 
                  userAge = ?
         
                WHERE
                    id = ?
                """;
        try {
            OraclePreparedStatement preparedStatement = (OraclePreparedStatement)
                    dbConnection.getContext().prepareStatement(updateStatement);

            preparedStatement.setInt(1, obj.getRating());
            preparedStatement.setString(2, obj.getComment());
            preparedStatement.setInt(2, obj.getUserAge());


            preparedStatement.executeUpdate();

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void delete(Review obj) {
        String deleteStatement = """
                DELETE FROM review
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
