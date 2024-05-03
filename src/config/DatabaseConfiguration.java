package config;

import java.sql.*;
public class DatabaseConfiguration{
    private static final String URL = "jdbc:oracle:thin:@localhost:1521:XE";
    private static final String USER = "testuser";
    private static final String PASSWROD = "testpassword";

    private static Connection context;
    private static DatabaseConfiguration instance;

    private DatabaseConfiguration(){
        try{
            context = DriverManager.getConnection(URL, USER, PASSWROD);
        } catch(SQLException ex){
            System.out.println("Error connectiong to database" + ex.getMessage());
        }
    }
    public static DatabaseConfiguration getInstance(){
        if(instance == null){
            instance = new DatabaseConfiguration();
        }
        return instance;
    }

    public static Connection getContext(){
        return context;
    }
    public static void setContext(Connection context){
        DatabaseConfiguration.context = context;
    }
    public static void setInstance(DatabaseConfiguration instance){
        DatabaseConfiguration.instance = instance;
    }
}