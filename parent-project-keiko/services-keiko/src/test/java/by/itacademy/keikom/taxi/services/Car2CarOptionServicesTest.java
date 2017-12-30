package by.itacademy.keikom.taxi.services;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import by.itacademy.keikom.taxi.dao.dbmodel.Brand;
import by.itacademy.keikom.taxi.dao.dbmodel.Car;
import by.itacademy.keikom.taxi.dao.dbmodel.Car2CarOption;
import by.itacademy.keikom.taxi.dao.dbmodel.CarOption;
import by.itacademy.keikom.taxi.dao.dbmodel.LegalEntity;
import by.itacademy.keikom.taxi.dao.dbmodel.Model;
import by.itacademy.keikom.taxi.dao.dbmodel.User;
import by.itacademy.keikom.taxi.services.impl.BrandServicesImpl;
import by.itacademy.keikom.taxi.services.impl.Car2CarOptionServicesImpl;
import by.itacademy.keikom.taxi.services.impl.CarOptionServicesImpl;
import by.itacademy.keikom.taxi.services.impl.CarServicesImpl;
import by.itacademy.keikom.taxi.services.impl.LegalEntityServicesImpl;
import by.itacademy.keikom.taxi.services.impl.ModelServicesImpl;
import by.itacademy.keikom.taxi.services.impl.UserServicesImpl;

public class Car2CarOptionServicesTest extends AbstractServicesTest {

	private static final Logger LOGGER = LoggerFactory.getLogger(Car2CarOptionServicesTest.class);
	private static final Car2CarOptionServicesImpl services = Car2CarOptionServicesImpl.getInstance();
	private List<Car2CarOption> list = new ArrayList<Car2CarOption>();
	private static CarServicesImpl carServices = CarServicesImpl.getInstance();

	private static Car car;
	private static CarOptionServicesImpl carOptionServices = CarOptionServicesImpl.getInstance();
	private static CarOption carOption;

	private static Model model = new Model();
	private static ModelServicesImpl modelServices = ModelServicesImpl.getInstance();

	private static LegalEntity legalEntity;
	private static LegalEntityServicesImpl legalEntityServices = LegalEntityServicesImpl.getInstance();

	private static User user;
	private static UserServicesImpl userServisec = UserServicesImpl.getInstance();

	private static Brand brand;
	private static BrandServicesImpl brandServices = BrandServicesImpl.getInstance();

	@BeforeClass
	public static void prepareTestData() throws ParseException {

		brand = createBrand();
		brandServices.save(brand);

		model = createModel(brand);
		modelServices.save(model);

		legalEntity = createLegalEntity();
		legalEntityServices.save(legalEntity);

		user = createUser();
		userServisec.save(user);

		car = createCar(user, model, legalEntity);
		carServices.save(car);

		carOption = createCarOption();
		carOptionServices.save(carOption);
	}

	@AfterClass
	public static void cleanTestData() {

		brandServices.delete(brand.getId());
		modelServices.delete(model.getId());
		legalEntityServices.delete(legalEntity.getId());
		userServisec.delete(user.getId());
		carServices.delete(car.getId());
		carOptionServices.delete(carOption.getId());
	}

	@Test
	public void testCRUD() {

		Car2CarOption obj = null;

		try {
			services.create(obj);
			Assert.fail();
		} catch (Exception e) {
			LOGGER.error("you cannot save the object entered all of the data");
		}
		services.create(obj);
		Assert.assertNotNull(services.getById(obj));
	}
}
