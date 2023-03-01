package com.epam.rd.java.basic.topic07.task01.db;

public enum DBQueries {
    GET_TEAM("SELECT id, name FROM teams WHERE name= (?)"),
    GET_USER("SELECT id, login FROM users WHERE login= (?)"),
    GET_ALL_TEAMS("SELECT id, name FROM teams ORDER BY name"),
    GET_ALL_USERS("SELECT id, login FROM users ORDER BY login"),
    INSERT_TEAM("INSERT INTO teams (name) VALUES (?)"),
    INSERT_USER("INSERT INTO users (login) VALUES (?)");

    private final String query;

    DBQueries(String query) {
        this.query=query;
    }

    public String getQuery() {
        return query;
    }

}
