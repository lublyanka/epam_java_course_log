package com.epam.rd.java.basic.topic07.task01.db;

import java.sql.SQLException;

public class DBException extends Exception {

    public DBException(SQLException e) {
        e.printStackTrace();
    }
}
