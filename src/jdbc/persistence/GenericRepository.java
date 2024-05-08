package jdbc.persistence;

import config.DatabaseConfiguration;
import jdbc.exceptions.InvalidDataException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface GenericRepository<T> {
    void add(T entity);
    T get(int id);
    ArrayList<T> getAll() throws RuntimeException, InvalidDataException;
    void update(T entity);
    void delete(T entity);

}

