package com.epam.rd.java.basic.topic07.task03.db;

import com.epam.rd.java.basic.topic07.task03.db.entity.Team;
import com.epam.rd.java.basic.topic07.task03.db.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class UsersTeamsDA {

    private static final String GET_TEAM_USERS = "SELECT ut.user_id AS id, u.login FROM users_teams ut " +
            "JOIN users u ON ut.user_id = u.id " +
            "WHERE ut.team_id = ?";
    private static final String GET_USER_TEAMS = "SELECT ut.team_id AS id, t.name FROM users_teams ut " +
            "JOIN teams t ON ut.team_id = t.id " +
            "WHERE ut.user_id = ?";
    private static final String SET_TEAM_FOR_USER = "INSERT INTO users_teams (user_id, team_id) VALUES (?, ?)";

    public UsersTeamsDA() {
    }

    List<Team> getUserTeams(User user, Connection connection) throws DBException {
        List<Team> teams = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(GET_USER_TEAMS)) {
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

    List<User> getTeamUsers(Team team, Connection connection) throws DBException {
        List<User> users = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(GET_TEAM_USERS)) {
            statement.setInt(1, team.getId());
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                User user = User.createUser(rs.getInt(Fields.USER_ID), rs.getString(Fields.USER_LOGIN));
                users.add(user);
            }
            rs.close();
            return users;
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

    boolean setTeamsForUser(User user, Team[] teams, Connection connection) throws DBException {
        for (Team team : teams) {
            try (PreparedStatement statement = connection.prepareStatement(SET_TEAM_FOR_USER)) {
                statement.setInt(1, user.getId());
                statement.setInt(2, team.getId());
                statement.execute();
            } catch (SQLException e) {
                throw new DBException(e);
            }
        }
        return true;
    }
}
