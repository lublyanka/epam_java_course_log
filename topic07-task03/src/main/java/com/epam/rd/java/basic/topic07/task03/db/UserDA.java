package com.epam.rd.java.basic.topic07.task03.db;

import com.epam.rd.java.basic.topic07.task03.db.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;

public class UserDA {

    private static final String GET_USER = "SELECT id, login FROM users WHERE login= (?)";
    private static final String DELETE_USER = "DELETE FROM users WHERE name IN (";
    private static final String INSERT_USER = "INSERT INTO users (login) VALUES (?)";
    private static final String GET_ALL_USERS = "SELECT id, login FROM users ORDER BY login";

    UserDA() {
    }

    List<User> findAllU(Connection connection) throws DBException {
        List<User> users = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(GET_ALL_USERS)) {
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                User user = User.createUser(rs.getString(Fields.USER_LOGIN));
                users.add(user);
            }
            rs.close();
            return users;
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

    User getUser(String login, Connection connection) throws DBException {
        try (PreparedStatement statement = connection.prepareStatement(GET_USER)) {
            statement.setString(1, login);
            ResultSet rs = statement.executeQuery();
            User user = null;
            while (rs.next()) {
                user = User.createUser(rs.getInt(Fields.USER_ID), rs.getString(Fields.USER_LOGIN));
            }
            rs.close();
            return user;
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

    boolean deleteUsers(User[] users, Connection connection) throws DBException {
        if (users.length == 0)
            throw new IllegalArgumentException();
        String namesToDelete = getNamesToDelete(users);
        try (PreparedStatement statement = connection.prepareStatement(DELETE_USER + namesToDelete + ")")) {
            statement.execute();
            return true;
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

    private static String getNamesToDelete(User[] users) {
        return Arrays.stream(users)
                .map(user -> "'" + user.getLogin() + "'")
                .reduce((a, b) -> String.valueOf(new StringJoiner(",").add(a).add(b))).orElse("");
    }

    boolean insertUser(User user, Connection connection) throws DBException {
        try (PreparedStatement statement = connection.prepareStatement(INSERT_USER)) {
            statement.setString(1, user.getLogin());
            statement.execute();
        } catch (SQLException e) {
            throw new DBException(e);
        }
        int userNewId = getUser(user.getLogin(), connection).getId();
        user.setId(userNewId);
        return true;
    }
}
