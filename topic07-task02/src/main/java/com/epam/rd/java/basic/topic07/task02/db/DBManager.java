package com.epam.rd.java.basic.topic07.task02.db;

import com.epam.rd.java.basic.topic07.task02.Constants;
import com.epam.rd.java.basic.topic07.task02.db.entity.Team;
import com.epam.rd.java.basic.topic07.task02.db.entity.User;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.*;

public class DBManager {

    private static DBManager instance;
    private static Connection connection;
    private final static Properties properties = new Properties();


    private DBManager() {
        try {
            properties.load(new FileInputStream(Constants.SETTINGS_FILE));
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

    public boolean insertUser(User user) throws DBException {
        try (PreparedStatement statement = connection.prepareStatement(DBQueries.INSERT_USER.getQuery())) {
            statement.setString(1, user.getLogin());
            statement.execute();
        } catch (SQLException e) {
            throw new DBException(e);
        }
        int userNewId = getUser(user.getLogin()).getId();
        user.setId(userNewId);
        return true;
    }

    public boolean deleteUsers(User... users) throws DBException {
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        if (users.length == 0)
            throw new IllegalArgumentException();
        String namesToDelete = Arrays.stream(users)
                .map(user -> "'" + user.getLogin() + "'")
                .reduce((a, b) -> String.valueOf(new StringJoiner(",").add(a).add(b))).get();
        try (PreparedStatement statement = connection.prepareStatement(DBQueries.DELETE_USER.getQuery() + namesToDelete + ")")) {
            statement.execute();
            connection.commit();
            connection.setAutoCommit(true);
            return true;
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException sqle) {
                throw new DBException(sqle);
            }
            throw new DBException(e);
        }
    }

    public User getUser(String login) throws DBException {
        try (PreparedStatement statement = connection.prepareStatement(DBQueries.GET_USER.getQuery())) {
            User user = null;
            statement.setString(1, login);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                user = User.createUser(rs.getInt(Fields.USER_ID), rs.getString(Fields.USER_LOGIN));
            }
            rs.close();
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
            while (rs.next()) {
                team = Team.createTeam(rs.getInt(Fields.TEAM_ID), rs.getString(Fields.TEAM_NAME));
            }
            rs.close();
            return team;
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

    public List<Team> findAllTeams() throws DBException {
        List<Team> teams = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(DBQueries.GET_ALL_TEAMS.getQuery())) {
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Team team = Team.createTeam(rs.getInt(Fields.TEAM_ID), rs.getString(Fields.TEAM_NAME));
                teams.add(team);
            }
            rs.close();
            return teams;
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

    public boolean insertTeam(Team team) throws DBException {
        try (PreparedStatement statement = connection.prepareStatement(DBQueries.INSERT_TEAM.getQuery())) {
            statement.setString(1, team.getName());
            statement.execute();
            int teamNewId = getTeam(team.getName()).getId();
            team.setId(teamNewId);
            return true;
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

    public boolean setTeamsForUser(User user, Team... teams) throws DBException {
        try {
            connection.setAutoCommit(false);
            for (Team team : teams) {
                try (PreparedStatement statement = connection.prepareStatement(DBQueries.SET_TEAM_FOR_USER.getQuery())) {
                    statement.setInt(1, user.getId());
                    statement.setInt(2, team.getId());
                    statement.execute();
                } catch (SQLException e) {
                    throw new DBException(e);
                }
            }

            connection.commit();
            connection.setAutoCommit(true);
            return true;

        } catch (SQLException e) {
            throw new DBException(e);
        }

    }

    public List<Team> getUserTeams(User user) throws DBException {
        List<Team> teams = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(DBQueries.GET_USER_TEAMS.getQuery())) {
            statement.setInt(1, user.getId());
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Team team = Team.createTeam(rs.getInt(Fields.TEAM_ID), rs.getString(Fields.TEAM_NAME));
                teams.add(team);
            }
            rs.close();
            return teams;
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

    public boolean deleteTeam(Team team) throws DBException {
        try (PreparedStatement statement = connection.prepareStatement(DBQueries.DELETE_TEAM.getQuery())) {
            statement.setString(1, team.getName());
            statement.execute();
            return true;
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

    public boolean updateTeam(Team team) throws DBException {
        try (PreparedStatement statement = connection.prepareStatement(DBQueries.UPDATE_TEAM.getQuery())) {
            statement.setString(1, team.getName());
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }
}