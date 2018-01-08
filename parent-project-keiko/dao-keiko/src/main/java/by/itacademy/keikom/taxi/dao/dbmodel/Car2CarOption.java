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
	public String toString() {
		return "Car2CarOption [carId=" + carId + ", carOptionId=" + carOptionId + "]";
	}
}
