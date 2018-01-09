package by.itacademy.keikom.taxi.dao.dbmodel;

import java.sql.Timestamp;

import by.itacademy.keikom.taxi.dao.enums.CarStatus;

public class Car {

	private Integer id;
	private Integer userId;
	private Integer releaseYear;
	private Integer modelId;
	private Integer legalEntityId;
	private CarStatus status;
	private Timestamp created;
	private Timestamp modified;

	public Car() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getReleaseYear() {
		return releaseYear;
	}

	public void setReleaseYear(Integer releaseYear) {
		this.releaseYear = releaseYear;
	}

	public Integer getModelId() {
		return modelId;
	}

	public void setModelId(Integer modelId) {
		this.modelId = modelId;
	}

	public Integer getLegalEntityId() {
		return legalEntityId;
	}

	public void setLegalEntityId(Integer legalEntityId) {
		this.legalEntityId = legalEntityId;
	}

	public CarStatus getStatus() {
		return status;
	}

	public void setStatus(CarStatus status) {
		this.status = status;
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
		return "Car [id=" + id + ", userId=" + userId + ", releaseYear=" + releaseYear + ", modelId=" + modelId
				+ ", legalEntityId=" + legalEntityId + ", status=" + status + ", created=" + created + ", modified="
				+ modified + "]";
	}
}
