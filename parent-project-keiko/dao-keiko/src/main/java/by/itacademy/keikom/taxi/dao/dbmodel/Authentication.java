package by.itacademy.keikom.taxi.dao.dbmodel;

import java.sql.Timestamp;

public class Authentication {

	private Integer userId;
	private String login;
	private String password;
	private Timestamp created;
	private Timestamp modified;

	public Authentication() {
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Timestamp getCreated() {
		return created;
	}

	public void setCreated(Timestamp created) {
		this.created = created;
	}

	public Timestamp getModified() {
		return modified;
	}

	public void setModified(Timestamp modified) {
		this.modified = modified;
	}

	@Override
	public String toString() {
		return "Authentication [userId=" + userId + ", login=" + login + ", password=" + password + ", created="
				+ created + ", modified=" + modified + "]";
	}
}
