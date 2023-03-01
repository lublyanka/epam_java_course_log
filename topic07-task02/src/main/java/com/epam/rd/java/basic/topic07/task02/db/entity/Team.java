package com.epam.rd.java.basic.topic07.task02.db.entity;

import java.util.Objects;

public class Team {

	private int id;

	private final String name;

	private Team(String teamName) {
		this.name = teamName;
		this.id = 0;
	}

	public Team(int id, String teamName) {
		this.id = id;
		this.name = teamName;
	}

	public static Team createTeam(String teamNameToAssign) {
		return new Team(teamNameToAssign);
	}

	public static Team createTeam(int id, String teamName) {
		return new Team(id, teamName);
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setId(int id) {
		this.id = id;
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