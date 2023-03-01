package com.epam.rd.java.basic.topic07.task02.db;

public enum DBQueries {
    GET_TEAM("SELECT id, name FROM teams WHERE name= (?)"),
    GET_USER("SELECT id, login FROM users WHERE login= (?)"),
    GET_USER_TEAMS("SELECT ut.team_id AS id, t.name FROM users_teams ut " +
            "JOIN teams t ON ut.team_id = t.id " +
            "WHERE ut.user_id = ?"),
    GET_ALL_TEAMS("SELECT id, name FROM teams ORDER BY name"),
    GET_ALL_USERS("SELECT id, login FROM users ORDER BY login"),
    INSERT_TEAM("INSERT INTO teams (name) VALUES (?)"),
    INSERT_USER("INSERT INTO users (login) VALUES (?)"),
    SET_TEAM_FOR_USER("INSERT INTO users_teams (user_id, team_id) VALUES (?, ?)"),
    UPDATE_TEAM("UPDATE teams SET name WHERE name = ?"),
    DELETE_TEAM("DELETE FROM teams WHERE name = ?"),
    DELETE_USER("DELETE FROM users WHERE name IN (");

    private final String query;

    DBQueries(String query) {
        this.query=query;
    }

    public String getQuery() {
        return query;
    }

}
