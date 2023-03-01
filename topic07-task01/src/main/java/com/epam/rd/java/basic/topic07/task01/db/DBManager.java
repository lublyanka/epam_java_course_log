package com.epam.rd.java.basic.topic07.task01.db;

import com.epam.rd.java.basic.topic07.task01.db.entity.Team;
import com.epam.rd.java.basic.topic07.task01.db.entity.User;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class DBManager {
    private static DBManager instance;
    private static Connection connection;
    private final static Properties properties = new Properties();


    private DBManager() {
        try {
            properties.load(new FileInputStream("app.properties"));
            String url = properties.getProperty("connection.url");
            if (url.isBlank())
                throw new IllegalArgumentException();
            connection = DriverManager.getConnection(url);
        } catch (FileNotFoundException fileNotFoundException) {
            throw new IllegalArgumentException("Property file wasn't found");
        } catch (SQLException | IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public static synchronized DBManager getInstance() {
        if (instance == null) {
            instance = new DBManager();
        } else return instance;

        return new DBManager();
    }

    public List<User> findAllUsers() throws DBException {
        List<User> users = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(DBQueries.GET_ALL_USERS.getQuery())) {
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                User user = User.createUser(rs.getString(Fields.USER_LOGIN));
                users.add(user);
            }
            return users;
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

    public boolean insertUser(User user) throws DBException {

        try (PreparedStatement statement = connection.prepareStatement(DBQueries.INSERT_USER.getQuery())) {
            statement.setString(1, user.getLogin());
            statement.execute();
            return true;
        } catch (SQLException e) {
            throw new DBException(e);
        }

        //TODO return false;
    }

    public User getUser(String login) throws DBException {
        try (PreparedStatement statement = connection.prepareStatement(DBQueries.GET_USER.getQuery())) {
            User user = null;
            statement.setString(1, login);
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                user = User.createUser(rs.getString(Fields.USER_LOGIN));
            }
            return user;
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

    public Team getTeam(String name) throws DBException {
        try (PreparedStatement statement = connection.prepareStatement(DBQueries.GET_TEAM.getQuery())) {
            Team team = null;
            statement.setString(1, name);
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                team = Team.createTeam(rs.getString(Fields.TEAM_NAME));
            }
            return team;
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

    public List<Team> findAllTeams() throws DBException {
        List<Team> teams = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(DBQueries.GET_ALL_TEAMS.getQuery())) {
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                Team team = Team.createTeam(rs.getString(Fields.TEAM_NAME));
                teams.add(team);
            }
            return teams;
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

    public boolean insertTeam(Team team) throws DBException {
        try (PreparedStatement statement = connection.prepareStatement(DBQueries.INSERT_TEAM.getQuery())) {
            statement.setString(1, team.getName());
            statement.execute();
            return true;
        } catch (SQLException e) {
            throw new DBException(e);
        }

        //TODO return false;
    }
}