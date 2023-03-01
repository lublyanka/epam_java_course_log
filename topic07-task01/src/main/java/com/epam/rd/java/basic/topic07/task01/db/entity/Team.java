package com.epam.rd.java.basic.topic07.task01.db.entity;

import java.util.Objects;

public class Team {

    private int id;

    private final String name;

    private Team(String teamName) {
        this.name = teamName;
    }

    public static Team createTeam(String teamNameToAssign) {
        return new Team(teamNameToAssign);
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Team team = (Team) o;
        return name.equals(team.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}