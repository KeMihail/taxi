package by.itacademy.keikom.taxi.services;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import by.itacademy.keikom.taxi.dao.dbmodel.Authentication;
import by.itacademy.keikom.taxi.dao.dbmodel.Brand;
import by.itacademy.keikom.taxi.dao.dbmodel.Car;
import by.itacademy.keikom.taxi.dao.dbmodel.Car2CarOption;
import by.itacademy.keikom.taxi.dao.dbmodel.CarOption;
import by.itacademy.keikom.taxi.dao.dbmodel.LegalEntity;
import by.itacademy.keikom.taxi.dao.dbmodel.Model;
import by.itacademy.keikom.taxi.dao.dbmodel.Order;
import by.itacademy.keikom.taxi.dao.dbmodel.Rate;
import by.itacademy.keikom.taxi.dao.dbmodel.User;
import by.itacademy.keikom.taxi.dao.enums.EBodyType;
import by.itacademy.keikom.taxi.dao.enums.ECarKit;
import by.itacademy.keikom.taxi.dao.enums.EEngineType;
import by.itacademy.keikom.taxi.dao.enums.UserRole;

public abstract class AbstractServicesTest {

	private static Brand brand = new Brand();
	private static Model model = new Model();
	private static LegalEntity legalEntity = new LegalEntity();
	private static User user = new User();
	private static Car car = new Car();
	private static Rate rate = new Rate();
	private static Authentication authentication = new Authentication();
	private static CarOption carOption = new CarOption();
	private static Car2CarOption obj = new Car2CarOption();
	private static Order order = new Order();

	public static Brand createBrand() {
		brand.setName("Рено");
		brand.setCreated(new Timestamp(new Date().getTime()));
		return brand;
	}

	public static Model createModel(Brand brand) {
		model.setName("Лагуна");
		model.setBodyType(EBodyType.МиниВен);
		model.setBrandId(brand.getId());
		model.setCarCit(ECarKit.Классическая);
		model.setEngineType(EEngineType.ГазБензин);
		return model;
	}

	public static LegalEntity createLegalEntity() {
		legalEntity.setName("ООО Такси");
		legalEntity.setAddress("г.Гродно");
		legalEntity.setEmail("Taxi@tut.by");
		legalEntity.setPhoneNumber("80297875512");
		return legalEntity;
	}

	public static User createUser() throws ParseException {
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

	public static User createUserClient() throws ParseException {
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

	public static Car createCar(User user, Model model, LegalEntity legalEntity) {
		car.setReleaseYear(2001);
		car.setUserId(user.getId());
		car.setModelId(model.getId());
		car.setLegalEntityId(legalEntity.getId());
		car.setDeleted(false);
		car.setStatus(false);
		return car;
	}

	public static Rate createRate() {
		rate.setName("Дневной");
		rate.setPriceKilometr(1.1);
		rate.setPriceLanding(2.0);
		rate.setPriceMinuteWait(0.3);
		return rate;
	}

	public static Authentication createAuthentication(User user) {
		authentication.setUserId(user.getId());
		authentication.setLogin("login");
		authentication.setPassword("password");
		return authentication;
	}

	public static CarOption createCarOption() {
		carOption.setName("Кондиционер");
		return carOption;
	}

	public static CarOption createCarOptionUpdate() {
		carOption.setName("Автомобиль Бизнес класса");
		return carOption;
	}

	public static Car2CarOption createCar2CarOption(Car car, CarOption carOption) {
		obj.setCarId(car.getId());
		obj.setCarOptionId(carOption.getId());
		return obj;
	}

	public static Order createOrder(Car car, Rate rate, User userClient) {

		Calendar instance = Calendar.getInstance();

		order.setArrivalAddress("г.Гродно");
		order.setDepartureAddress("г.Минск");
		order.setCarId(car.getId());
		order.setDeleted(false);
		order.setDistance(265.0);
		order.setInactivityMinutes(15);
		instance.add(Calendar.MINUTE, 320);
		order.setOrderEnd(new Timestamp(instance.getTimeInMillis()));
		order.setRateId(rate.getId());
		order.setUserId(userClient.getId());
		return order;
	}
}
