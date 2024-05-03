package jdbc.persistence;
import jdbc.model.Employee;
import jdbc.persistence.ClientRepository;
import jdbc.persistence.GenericRepository;
import oracle.jdbc.OraclePreparedStatement;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeRepository implements GenericRepository<Employee> {
    private static EmployeeRepository instance = null;
    private EmployeeRepository() {}

    public static EmployeeRepository getInstance(){
        if(instance == null){
            instance = new EmployeeRepository();
        }

        return instance;
    }

    @Override
    public void add(Employee obj) {
        String insertUser = """
                INSERT INTO App_User VALUES (user_sequence.nextval, ?, ?, ?, ?, ?)
                """;
        try {
            oracle.jdbc.internal.OraclePreparedStatement preparedStatement = (oracle.jdbc.internal.OraclePreparedStatement) dbConnection.getContext().prepareStatement(insertUser);

            preparedStatement.setString(1, obj.getUsername());
            preparedStatement.setString(2, obj.getEmail());
            preparedStatement.setString(3, obj.getPassword());
            preparedStatement.setString(4, obj.getPhoneNumber());
            preparedStatement.setInt(5, obj.getAge());


            preparedStatement.executeUpdate();
            obj.setUser_id(retrieveLastId("user_sequence"));
        }
        catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        String insertPod = """
                INSERT INTO employee VALUES(employee_sequence.nextval, ?, ?, ?, ?, ?)
                """;
        try {
            oracle.jdbc.internal.OraclePreparedStatement preparedStatement = (oracle.jdbc.internal.OraclePreparedStatement)
                    dbConnection.getContext().prepareStatement(insertPod);

            preparedStatement.setInt(1, obj.getUser_id());
            preparedStatement.setString(2, obj.getJobTitle());
            preparedStatement.setDate(3, obj.getHiringDate());
            preparedStatement.setInt(4, obj.getSalary());
            preparedStatement.setInt(5, obj.getDailyWorkHours());

            preparedStatement.executeUpdate();

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }


//
    @Override
    public Employee get(int id) {
        String selectQuery = """
                SELECT u.user_id, u.username, u.email,
                    u.password, u.age, u.phoneNumber, e.employee_id,
                        e.jobTitle, e.hiringDate, e.salary, e.dailyWorkHours
                FROM employee e, App_User u WHERE e.id = u.id
                AND e.employee_id = ?
                """;
        try{
            OraclePreparedStatement preparedStatement = (OraclePreparedStatement)
                    dbConnection.getContext().prepareStatement(selectQuery);

            preparedStatement.setInt(1, id);

            ResultSet res = preparedStatement.executeQuery();

            if (res.next()){

                return new Employee(
                        res.getInt(1),
                        res.getString(2),
                        res.getString(3),
                        res.getString(4),
                        res.getString(5),
                        res.getInt(6),
                        res.getInt(7),
                        res.getString(8),
                        res.getDate(9),
                        res.getInt(10),
                        res.getInt(11)
                );
            }
            else{
                throw new RuntimeException();
            }

        }catch (SQLException ex){
            throw new RuntimeException(ex);
        }
    }
//
    @Override
    public ArrayList<Employee> getAll() {
        String selectQuery = """
                    SELECT u.user_id, u.username, u.email,
                u.age, u.phoneNumber, u.password, e.employee_id,
                   e.jobTitle, e.hiringDate, e.salary, e.dailyWorkHours
                  FROM employee e, App_User u WHERE e.user_id = u.user_id
                """;

        try{
            OraclePreparedStatement preparedStatement = (OraclePreparedStatement)
                    dbConnection.getContext().prepareStatement(selectQuery);

            ResultSet res = preparedStatement.executeQuery();

            ArrayList<Employee> employees = new ArrayList<>();

            while(res.next()){
                Employee employee = new Employee(
                        res.getInt(1),
                        res.getString(2),
                        res.getString(3),
                        res.getString(4),
                        res.getString(5),
                        res.getInt(6),
                        res.getInt(7),
                        res.getString(8),
                        res.getDate(9),
                        res.getInt(10),
                        res.getInt(11)
                );
                employees.add(employee);
            }
            return employees;
        }catch (SQLException ex){
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void update(Employee obj) {
        String updateStatementPod = """
                    UPDATE employee
                    SET
                       jobTitle = ?,
                       hiringDate = ?
                    WHERE
                        employee_id = ?
                """;
        try{
            OraclePreparedStatement preparedStatement = (OraclePreparedStatement)
                    dbConnection.getContext().prepareStatement(updateStatementPod);

            preparedStatement.setString(1, obj.getJobTitle());
            preparedStatement.setDate(2, obj.getHiringDate());
            preparedStatement.setInt(3, obj.getEmployee_id());

            preparedStatement.executeUpdate();
        }catch (SQLException ex){
            throw new RuntimeException(ex);
        }

        String updateStatementTrk = """
                    UPDATE App_User
                    SET
                        username = ?,
                        email = ?,
                        password = ?
                    WHERE
                        user_id = ?
                """;
        try{
            OraclePreparedStatement preparedStatement = (OraclePreparedStatement)
                    dbConnection.getContext().prepareStatement(updateStatementTrk);

            preparedStatement.setString(1, obj.getUsername());
            preparedStatement.setString(2, obj.getEmail());
            preparedStatement.setString(3, obj.getPassword());
            preparedStatement.setInt(4, obj.getUser_id());

            preparedStatement.executeUpdate();

        }catch (SQLException ex){
            throw new RuntimeException(ex);
        }

    }

    @Override
    public void delete(Employee obj) {
        String deleteStatement = """
                DELETE FROM employee
                WHERE employee_id = ?
                """;
        try{
            OraclePreparedStatement preparedStatement = (OraclePreparedStatement)
                    dbConnection.getContext().prepareStatement(deleteStatement);

            preparedStatement.setInt(1, obj.getEmployee_id());

            preparedStatement.executeUpdate();
        }catch (SQLException ex){
            throw new RuntimeException(ex);
        }

        String deleteStatementTrk = """
                DELETE FROM App_User
                WHERE user_id = ?
                """;
        try{
            OraclePreparedStatement preparedStatement = (OraclePreparedStatement)
                    dbConnection.getContext().prepareStatement(deleteStatementTrk);

            preparedStatement.setInt(1, obj.getUser_id());

            preparedStatement.executeUpdate();
        }catch (SQLException ex){
            throw new RuntimeException(ex);
        }
    }
}