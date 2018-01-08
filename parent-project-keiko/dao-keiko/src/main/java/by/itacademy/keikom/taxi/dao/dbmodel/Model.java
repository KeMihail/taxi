package by.itacademy.keikom.taxi.dao.dbmodel;

import by.itacademy.keikom.taxi.dao.enums.EBodyType;
import by.itacademy.keikom.taxi.dao.enums.ECarKit;
import by.itacademy.keikom.taxi.dao.enums.EEngineType;

import java.sql.Timestamp;

public class Model {

	private Integer id;
	private String name;
	private ECarKit carCit;
	private EEngineType engineType;
	private EBodyType BodyType;
	private Integer brandId;
	private Timestamp created;
	private Timestamp modified;

	public Model() {
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

	public ECarKit getCarCit() {
		return carCit;
	}

	public void setCarCit(ECarKit carCit) {
		this.carCit = carCit;
	}

	public EEngineType getEngineType() {
		return engineType;
	}

	public void setEngineType(EEngineType engineType) {
		this.engineType = engineType;
	}

	public EBodyType getBodyType() {
		return BodyType;
	}

	public void setBodyType(EBodyType eBodyType) {
		BodyType = eBodyType;
	}

	public Integer getBrandId() {
		return brandId;
	}

	public void setBrandId(Integer brandId) {
		this.brandId = brandId;
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
		return "Model [id=" + id + ", name=" + name + ", carCit=" + carCit + ", engineType=" + engineType
				+ ", EBodyType=" + BodyType + ", brandId=" + brandId + ", created=" + created + ", modified=" + modified
				+ "]";
	}
}
