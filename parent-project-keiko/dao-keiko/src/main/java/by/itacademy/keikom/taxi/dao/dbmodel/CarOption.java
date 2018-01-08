package by.itacademy.keikom.taxi.dao.dbmodel;

import java.sql.Timestamp;

public class CarOption {

	private Integer id;
	private String name;
	private Timestamp created;
	private Timestamp modified;

	public CarOption() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
		return "CarOption [id=" + id + ", name=" + name + ", created=" + created + ", modified=" + modified + "]";
	}
}