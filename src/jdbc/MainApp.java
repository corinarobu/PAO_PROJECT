package jdbc;

import config.DatabaseConfiguration;

import java.sql.Connection;

public class MainApp {

    public static void main(String[] args) {
        Connection connection = DatabaseConfiguration.getContext();
        if (connection != null) {
            System.out.println("Conexiunea cu baza de date este activă!");
        } else {
            System.out.println("Nu s-a putut stabili conexiunea cu baza de date.");
        }

    }
}
