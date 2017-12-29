package by.itacademy.keikom.taxi.dao.dbmodel;

public class Car2CarOption {

	private Integer carId;
	private Integer carOptionId;

	public Car2CarOption() {
	}

	public Integer getCarId() {
		return carId;
	}

	public void setCarId(Integer carId) {
		this.carId = carId;
	}

	public Integer getCarOptionId() {
		return carOptionId;
	}

	public void setCarOptionId(Integer carOptionId) {
		this.carOptionId = carOptionId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((carId == null) ? 0 : carId.hashCode());
		result = prime * result + ((carOptionId == null) ? 0 : carOptionId.hashCode());
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
		Car2CarOption other = (Car2CarOption) obj;
		if (carId == null) {
			if (other.carId != null)
				return false;
		} else if (!carId.equals(other.carId))
			return false;
		if (carOptionId == null) {
			if (other.carOptionId != null)
				return false;
		} else if (!carOptionId.equals(other.carOptionId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Car2CarOption [carId=" + carId + ", carOptionId=" + carOptionId + "]";
	}
}
