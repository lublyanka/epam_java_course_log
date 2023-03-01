package com.epam.rd.java.basic.topic07.task03.db;

import com.epam.rd.java.basic.topic07.task03.db.entity.Team;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TeamDA{

    private static final String GET_TEAM = "SELECT id, name FROM teams WHERE name= (?)";
    private static final String GET_ALL_TEAMS = "SELECT id, name FROM teams ORDER BY name";
    private static final String DELETE_TEAM = "DELETE FROM teams WHERE name = ?";
    private static final String INSERT_TEAM = "INSERT INTO teams (name) VALUES (?)";
    private static final String UPDATE_TEAM_NAME = "UPDATE teams SET name= ? WHERE id = ?";


    TeamDA() {
    }

    Team getTeam(String name, Connection connection) throws DBException {
        try (PreparedStatement statement = connection.prepareStatement(GET_TEAM)) {
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

    List<Team> findAllTeams(Connection connection) throws DBException {
        List<Team> teams = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(GET_ALL_TEAMS)) {
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

    boolean deleteTeam(Team team, Connection connection) throws DBException {
        try (PreparedStatement statement = connection.prepareStatement(DELETE_TEAM)) {
            statement.setString(1, team.getName());
            statement.execute();
            return true;
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

    boolean insertTeam(Team team, Connection connection) throws DBException {
        try (PreparedStatement statement = connection.prepareStatement(INSERT_TEAM)) {
            statement.setString(1, team.getName());
            statement.execute();
            int teamNewId = getTeam(team.getName(), connection).getId();
            team.setId(teamNewId);
            return true;
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

    boolean updateTeamName(Team team, Connection connection) throws DBException {
        try (PreparedStatement statement = connection.prepareStatement(UPDATE_TEAM_NAME)) {
            statement.setString(1, team.getName());
            statement.setInt(2, team.getId());
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }
}
