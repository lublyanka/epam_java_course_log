package com.epam.rd.java.basic.topic07.task02.db.entity;

import java.util.Objects;

public class User {

	private int id;

	private final String login;

	private User(String login) {
		this.login = login;
		this.id = 0;
	}

	public User(int id, String login) {
		this.login = login;
		this.id = id;
	}

	public static User createUser(String loginToAssign) {
		return new User(loginToAssign);
	}

	public static User createUser(int idToAssign, String loginToAssign) {
		return new User(idToAssign, loginToAssign);
	}

	public int getId() {
		return id;
	}
	public String getLogin() {
		return this.login;
	}

	public void setId(int id) {
		this.id = id;
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