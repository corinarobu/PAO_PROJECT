package jdbc.exceptions;

import java.sql.SQLException;

public class InvalidDataException extends Exception {
    public InvalidDataException(String message) {
        super(message);
    }

}
