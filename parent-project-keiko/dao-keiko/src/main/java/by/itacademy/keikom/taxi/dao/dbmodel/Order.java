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
		this.orderTime = orderTime;
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

	public Timestamp getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(Timestamp orderTime) {
		this.orderTime = orderTime;
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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((arrivalAddress == null) ? 0 : arrivalAddress.hashCode());
		result = prime * result + ((carId == null) ? 0 : carId.hashCode());
		result = prime * result + ((created == null) ? 0 : created.hashCode());
		result = prime * result + ((deleted == null) ? 0 : deleted.hashCode());
		result = prime
				* result
				+ ((departureAddress == null) ? 0 : departureAddress.hashCode());
		result = prime * result
				+ ((distance == null) ? 0 : distance.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime
				* result
				+ ((inactivityMinutes == null) ? 0 : inactivityMinutes
						.hashCode());
		result = prime * result
				+ ((modified == null) ? 0 : modified.hashCode());
		result = prime * result
				+ ((orderBegin == null) ? 0 : orderBegin.hashCode());
		result = prime * result
				+ ((orderEnd == null) ? 0 : orderEnd.hashCode());
		result = prime * result
				+ ((orderTime == null) ? 0 : orderTime.hashCode());
		result = prime * result + ((rateId == null) ? 0 : rateId.hashCode());
		result = prime * result + ((summ == null) ? 0 : summ.hashCode());
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
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
		Order other = (Order) obj;
		if (arrivalAddress == null) {
			if (other.arrivalAddress != null)
				return false;
		} else if (!arrivalAddress.equals(other.arrivalAddress))
			return false;
		if (carId == null) {
			if (other.carId != null)
				return false;
		} else if (!carId.equals(other.carId))
			return false;
		if (created == null) {
			if (other.created != null)
				return false;
		} else if (!created.equals(other.created))
			return false;
		if (deleted == null) {
			if (other.deleted != null)
				return false;
		} else if (!deleted.equals(other.deleted))
			return false;
		if (departureAddress == null) {
			if (other.departureAddress != null)
				return false;
		} else if (!departureAddress.equals(other.departureAddress))
			return false;
		if (distance == null) {
			if (other.distance != null)
				return false;
		} else if (!distance.equals(other.distance))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (inactivityMinutes == null) {
			if (other.inactivityMinutes != null)
				return false;
		} else if (!inactivityMinutes.equals(other.inactivityMinutes))
			return false;
		if (modified == null) {
			if (other.modified != null)
				return false;
		} else if (!modified.equals(other.modified))
			return false;
		if (orderBegin == null) {
			if (other.orderBegin != null)
				return false;
		} else if (!orderBegin.equals(other.orderBegin))
			return false;
		if (orderEnd == null) {
			if (other.orderEnd != null)
				return false;
		} else if (!orderEnd.equals(other.orderEnd))
			return false;
		if (orderTime == null) {
			if (other.orderTime != null)
				return false;
		} else if (!orderTime.equals(other.orderTime))
			return false;
		if (rateId == null) {
			if (other.rateId != null)
				return false;
		} else if (!rateId.equals(other.rateId))
			return false;
		if (summ == null) {
			if (other.summ != null)
				return false;
		} else if (!summ.equals(other.summ))
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", carId=" + carId + ", userId=" + userId
				+ ", orderTime=" + orderTime + ", orderBegin=" + orderBegin
				+ ", orderEnd=" + orderEnd + ", distance=" + distance
				+ ", summ=" + summ + ", rateId=" + rateId
				+ ", departureAddress=" + departureAddress
				+ ", arrivalAddress=" + arrivalAddress + ", inactivityMinutes="
				+ inactivityMinutes + ", deleted=" + deleted + ", created="
				+ created + ", modified=" + modified + "]";
	}
}
