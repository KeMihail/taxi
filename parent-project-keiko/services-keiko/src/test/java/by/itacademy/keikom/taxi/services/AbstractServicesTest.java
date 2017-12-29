package by.itacademy.keikom.taxi.services;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import by.itacademy.keikom.taxi.dao.dbmodel.Brand;
import by.itacademy.keikom.taxi.dao.dbmodel.Car;
import by.itacademy.keikom.taxi.dao.dbmodel.LegalEntity;
import by.itacademy.keikom.taxi.dao.dbmodel.Model;
import by.itacademy.keikom.taxi.dao.dbmodel.User;
import by.itacademy.keikom.taxi.dao.enums.EBodyType;
import by.itacademy.keikom.taxi.dao.enums.ECarKit;
import by.itacademy.keikom.taxi.dao.enums.EEngineType;

public abstract class AbstractServicesTest {

	private static Brand brand = new Brand();
	private static Model model = new Model();
	private static LegalEntity legalEntity = new LegalEntity();
	private static User user = new User();
	private static Car car = new Car();

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
}
