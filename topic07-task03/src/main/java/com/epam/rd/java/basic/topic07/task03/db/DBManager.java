package com.epam.rd.java.basic.topic07.task03.db;

import com.epam.rd.java.basic.topic07.task03.CheckedFunction;
import com.epam.rd.java.basic.topic07.task03.db.entity.Team;
import com.epam.rd.java.basic.topic07.task03.db.entity.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class DBManager {

    private static DBManager instance;
    private final PropertiesLoader properties;
    private static UserDA userDA;
    private static TeamDA teamDA;
    private static UsersTeamsDA usersTeamsDA;


    private DBManager() {
        properties = PropertiesLoader.getInstance();
        userDA = new UserDA();
        teamDA = new TeamDA();
        usersTeamsDA = new UsersTeamsDA();
    }

    public static synchronized DBManager getInstance() {
        if (instance == null) {
            instance = new DBManager();
        } else return instance;

        return new DBManager();
    }

    public List<User> findAllUsers() throws DBException {
        return execute((con) -> userDA.findAllU(con));
    }

    public boolean insertUser(User user) throws DBException {
        return execute((con) -> userDA.insertUser(user, con));
    }

    public boolean deleteUsers(User... users) throws DBException {
        /*try (Connection c = DriverManager.getConnection(url)) {
            try {
                return userDA.deleteUsers(users, c);
            } catch (DBException e) {
                connection.rollback();
                throw new DBException(e);
            } catch (Exception e) {
                throw new DBException(e);
            }
        } catch (SQLException e) {
            throw new DBException(e);
        }*/

        return execute((con) -> userDA.deleteUsers(users, con));
    }

    public User getUser(String login) throws DBException {
        return execute((con) -> userDA.getUser(login, con));
    }

    public Team getTeam(String name) throws DBException {
        return execute((con) -> teamDA.getTeam(name, con));
    }

    public List<Team> findAllTeams() throws DBException {
        return execute((con) -> teamDA.findAllTeams(con));
    }

    public boolean insertTeam(Team team) throws DBException {
        return execute((con) -> teamDA.insertTeam(team, con));
    }


    public boolean setTeamsForUser(User user, Team... teams) throws DBException {
        return execute((con) -> usersTeamsDA.setTeamsForUser(user, teams, con));

    }

    public List<Team> getUserTeams(User user) throws DBException {
        return execute((con) -> usersTeamsDA.getUserTeams(user, con));
    }

    public List<User> getTeamUsers(Team team) throws DBException {
        return execute((con) -> usersTeamsDA.getTeamUsers(team, con));
    }

    public boolean deleteTeam(Team team) throws DBException {
        return execute((con) -> teamDA.deleteTeam(team, con));
    }

    public boolean updateTeam(Team team) throws DBException {
        return execute((con) -> teamDA.updateTeamName(team, con));
    }

    private <T> T execute(CheckedFunction<Connection, T> action) throws DBException {
        try (Connection con = DriverManager.getConnection(properties.getProperties().getProperty("connection.url"))) {
            try {
                con.setAutoCommit(false);
                T result = action.apply(con);
                con.commit();
                //con.setAutoCommit(true);
                return result;
            } catch (DBException e) {
                con.rollback();
                throw new DBException(e);
            } catch (Exception e) {
                throw new DBException(e);
            }
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }
}