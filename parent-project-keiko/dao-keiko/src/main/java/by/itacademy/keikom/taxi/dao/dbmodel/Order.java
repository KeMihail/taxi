package by.itacademy.keikom.taxi.dao.dbmodel;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class Order {

	private Integer id;
	private Integer carId;
	private Integer userId;
	private Timestamp orderBegin;
	private Timestamp orderEnd;
	private Double distance;
	private Double summ;
	private Integer rateId;
	private String departureAddress;
	private String arrivalAddress;
	private Integer inactivityMinutes;
	private Boolean deleted;
	private Timestamp created;
	private Timestamp modified;

	public Order() {
	}

	public Order(Integer id, Integer carId, Integer userId,
			Timestamp orderTime, Timestamp orderBegin, Timestamp orderEnd,
			Double distance, Double summ, Integer rateId,
			String departureAddress, String arrivalAddress,
			Integer inactivityMinutes, Boolean deleted, Timestamp created,
			Timestamp modified) {
		this.id = id;
		this.carId = carId;
		this.userId = userId;
		this.orderBegin = orderBegin;
		this.orderEnd = orderEnd;
		this.distance = distance;
		this.summ = summ;
		this.rateId = rateId;
		this.departureAddress = departureAddress;
		this.arrivalAddress = arrivalAddress;
		this.inactivityMinutes = inactivityMinutes;
		this.deleted = deleted;
		this.created = created;
		this.modified = modified;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCarId() {
		return carId;
	}

	public void setCarId(Integer carId) {
		this.carId = carId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Timestamp getOrderBegin() {
		return orderBegin;
	}

	public void setOrderBegin(Timestamp orderBegin) {
		this.orderBegin = orderBegin;
	}

	public Timestamp getOrderEnd() {
		return orderEnd;
	}

	public void setOrderEnd(Timestamp orderEnd) {
		this.orderEnd = orderEnd;
	}

	public Double getDistance() {
		return distance;
	}

	public void setDistance(Double distance) {
		this.distance = distance;
	}

	public Double getSumm() {
		return summ;
	}

	public void setSumm(Double summ) {
		this.summ = summ;
	}

	public Integer getRateId() {
		return rateId;
	}

	public void setRateId(Integer rateId) {
		this.rateId = rateId;
	}

	public String getDepartureAddress() {
		return departureAddress;
	}

	public void setDepartureAddress(String departureAddress) {
		this.departureAddress = departureAddress;
	}

	public String getArrivalAddress() {
		return arrivalAddress;
	}

	public void setArrivalAddress(String arrivalAddress) {
		this.arrivalAddress = arrivalAddress;
	}

	public Integer getInactivityMinutes() {
		return inactivityMinutes;
	}

	public void setInactivityMinutes(Integer inactivityMinutes) {
		this.inactivityMinutes = inactivityMinutes;
	}

	public Boolean getDeleted() {
		return deleted;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
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
		return "Order [id=" + id + ", carId=" + carId + ", userId=" + userId
				+ ", orderTime=" + ", orderBegin=" + orderBegin + ", orderEnd="
				+ orderEnd + ", distance=" + distance + ", summ=" + summ
				+ ", rateId=" + rateId + ", departureAddress="
				+ departureAddress + ", arrivalAddress=" + arrivalAddress
				+ ", inactivityMinutes=" + inactivityMinutes + ", deleted="
				+ deleted + ", created=" + created + ", modified=" + modified
				+ "]";
	}
}
