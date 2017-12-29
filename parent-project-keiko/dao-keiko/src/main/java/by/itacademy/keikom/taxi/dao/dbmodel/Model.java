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

	public Model(Integer id, String name, ECarKit carCit, EEngineType engineType, EBodyType BodyType, Integer brandId,
			Timestamp created, Timestamp modified) {
		super();
		this.id = id;
		this.name = name;
		this.carCit = carCit;
		this.engineType = engineType;
		this.BodyType = BodyType;
		this.brandId = brandId;
		this.created = created;
		this.modified = modified;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((BodyType == null) ? 0 : BodyType.hashCode());
		result = prime * result + ((brandId == null) ? 0 : brandId.hashCode());
		result = prime * result + ((carCit == null) ? 0 : carCit.hashCode());
		result = prime * result + ((created == null) ? 0 : created.hashCode());
		result = prime * result + ((engineType == null) ? 0 : engineType.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((modified == null) ? 0 : modified.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Model other = (Model) obj;
		if (BodyType != other.BodyType)
			return false;
		if (brandId == null) {
			if (other.brandId != null)
				return false;
		} else if (!brandId.equals(other.brandId))
			return false;
		if (carCit != other.carCit)
			return false;
		if (created == null) {
			if (other.created != null)
				return false;
		} else if (!created.equals(other.created))
			return false;
		if (engineType != other.engineType)
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (modified == null) {
			if (other.modified != null)
				return false;
		} else if (!modified.equals(other.modified))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
}
