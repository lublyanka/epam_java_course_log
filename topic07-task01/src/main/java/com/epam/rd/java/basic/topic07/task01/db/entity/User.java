package com.epam.rd.java.basic.topic07.task01.db.entity;

import java.util.Objects;

public class User {

    private int id;

    private final String login;

    private User(String login) {
        this.login = login;
    }

    public static User createUser(String loginToAssign) {
        return new User(loginToAssign);
    }

    public String getLogin() {
        return this.login;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return login.equals(user.login);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login);
    }
}