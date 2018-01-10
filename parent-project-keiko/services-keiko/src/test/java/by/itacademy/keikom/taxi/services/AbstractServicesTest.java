package by.itacademy.keikom.taxi.services;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import by.itacademy.keikom.taxi.dao.dbmodel.Authentication;
import by.itacademy.keikom.taxi.dao.dbmodel.Brand;
import by.itacademy.keikom.taxi.dao.dbmodel.Car;
import by.itacademy.keikom.taxi.dao.dbmodel.Car2CarOption;
import by.itacademy.keikom.taxi.dao.dbmodel.CarOption;
import by.itacademy.keikom.taxi.dao.dbmodel.Costs;
import by.itacademy.keikom.taxi.dao.dbmodel.LegalEntity;
import by.itacademy.keikom.taxi.dao.dbmodel.Model;
import by.itacademy.keikom.taxi.dao.dbmodel.Order;
import by.itacademy.keikom.taxi.dao.dbmodel.Rate;
import by.itacademy.keikom.taxi.dao.dbmodel.User;
import by.itacademy.keikom.taxi.dao.enums.BodyType;
import by.itacademy.keikom.taxi.dao.enums.CarKit;
import by.itacademy.keikom.taxi.dao.enums.CarStatus;
import by.itacademy.keikom.taxi.dao.enums.EngineType;
import by.itacademy.keikom.taxi.dao.enums.UserRole;

public abstract class AbstractServicesTest {

	public Brand createBrand() {

		Brand brand = new Brand();
		brand.setName("Рено");

		return brand;
	}

	public Model createModel(Brand brand) {

		Model model = new Model();
		model.setName("Лагуна");
		model.setBodyType(BodyType.Minivan);
		model.setBrandId(brand.getId());
		model.setCarCit(CarKit.Classic);
		model.setEngineType(EngineType.Diesel);
		return model;
	}

	public LegalEntity createLegalEntity() {

		LegalEntity legalEntity = new LegalEntity();
		legalEntity.setName("ООО Такси");
		legalEntity.setAddress("г.Гродно");
		legalEntity.setEmail("Taxi@tut.by");
		legalEntity.setPhoneNumber("80297875512");
		return legalEntity;
	}

	public User createUser() throws ParseException {

		User user = new User();
		user.setName("Миша");
		user.setAddress("г.Гродно");
		user.setBirthday(new Timestamp(new SimpleDateFormat("yyyy-MM-dd").parse("1984-07-28").getTime()));
		user.setEmail("Mihaila4038@yandex.ru");
		user.setLastName("Кейко");
		user.setPhoneNumber("80297875512");
		user.setDeleted(false);
		user.setRole(UserRole.director);
		return user;
	}

	public User createUserClient() throws ParseException {

		User user = new User();
		user.setName("Оля");
		user.setAddress("г.Гродно");
		user.setBirthday(new Timestamp(new SimpleDateFormat("yyyy-MM-dd").parse("1984-04-22").getTime()));
		user.setEmail("Olga@yandex.ru");
		user.setLastName("Кейко");
		user.setPhoneNumber("80445006793");
		user.setDeleted(false);
		user.setRole(UserRole.passenger);
		return user;
	}

	public Car createCar(User user, Model model, LegalEntity legalEntity) {

		Car car = new Car();
		car.setReleaseYear(2001);
		car.setUserId(user.getId());
		car.setModelId(model.getId());
		car.setLegalEntityId(legalEntity.getId());
		car.setStatus(CarStatus.Online);
		return car;
	}

	public Rate createRate() {

		Rate rate = new Rate();
		rate.setName("Дневной");
		rate.setPriceKilometr(1.1);
		rate.setPriceLanding(2.0);
		rate.setPriceMinuteWait(0.3);
		return rate;
	}

	public Authentication createAuthentication(User user) {

		Authentication authentication = new Authentication();
		authentication.setUserId(user.getId());
		authentication.setLogin("login");
		authentication.setPassword("password");
		return authentication;
	}

	public CarOption createCarOption() {

		CarOption carOption = new CarOption();
		carOption.setName("Кондиционер");
		return carOption;
	}

	public CarOption createCarOptionUpdate() {

		CarOption carOption = new CarOption();
		carOption.setName("Автомобиль Бизнес класса");
		return carOption;
	}

	public Car2CarOption createCar2CarOption(Car car, CarOption carOption) {

		Car2CarOption obj = new Car2CarOption();
		obj.setCarId(car.getId());
		obj.setCarOptionId(carOption.getId());
		return obj;
	}

	public Order createOrder(Car car, Rate rate, User userClient) {

		Order order = new Order();
		Calendar instance = Calendar.getInstance();
		instance.add(Calendar.MINUTE, 5);

		order.setCarId(car.getId());
		order.setUserId(userClient.getId());
		order.setDepartureAddress("Соломовой 40/38");
		order.setArrivalAddress("Клецкова 70/125");
		order.setOrderBegin(new Timestamp(instance.getTimeInMillis()));

		instance.add(Calendar.MINUTE, 10);

		order.setOrderEnd(new Timestamp(instance.getTimeInMillis()));
		order.setDistanceOrder(12.3);
		order.setDistancePaid(7.4);
		order.setInactivityMinutes(5);
		order.setRateId(rate.getId());
		order.setDeleted(false);
		return order;
	}

	public Costs createCosts(Car car) {

		Costs costs = new Costs();
		costs.setCarId(car.getId());
		costs.setTaxes(150.0);
		costs.setTechnicalInspection(70.0);
		costs.setInsurance(40.0);
		costs.setCarService(90.5);
		costs.setPretripInspection(80.4);
		costs.setSalaryDriver(500.0);
		costs.setFuelConsumption(4.5);
		costs.setOther(40.2);

		return costs;
	}
}
