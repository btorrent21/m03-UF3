package com.pluralcamp.daw.persistence.exceptions;

public class DAOException extends Exception{
    

    public DAOException (String message, Throwable cause) {
        super (message, cause);
    }

    public DAOException (Throwable cause) {
        this (cause.toString(), cause);
    }

    public DAOException (String message) {
        this (message, null);
    }

    public DAOException() {
        this ("se ha producido un error en la capa de persistencia", null);
    }
}
