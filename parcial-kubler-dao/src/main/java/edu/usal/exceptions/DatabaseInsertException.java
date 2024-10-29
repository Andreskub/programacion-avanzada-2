package edu.usal.exceptions;

public class DatabaseInsertException extends Exception {

    public DatabaseInsertException(String message) {
        super(message);
    }

    public DatabaseInsertException(String message, Throwable cause) {
        super(message, cause);
    }
}
