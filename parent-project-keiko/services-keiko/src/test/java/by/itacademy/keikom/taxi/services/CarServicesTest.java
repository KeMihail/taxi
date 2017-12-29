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
import by.itacademy.keikom.taxi.dao.dbmodel.LegalEntity;
import by.itacademy.keikom.taxi.dao.dbmodel.Model;
import by.itacademy.keikom.taxi.dao.dbmodel.User;
import by.itacademy.keikom.taxi.dao.enums.EBodyType;
import by.itacademy.keikom.taxi.dao.enums.ECarKit;
import by.itacademy.keikom.taxi.dao.enums.EEngineType;
import by.itacademy.keikom.taxi.services.impl.BrandServicesImpl;
import by.itacademy.keikom.taxi.services.impl.CarServicesImpl;
import by.itacademy.keikom.taxi.services.impl.LegalEntityServicesImpl;
import by.itacademy.keikom.taxi.services.impl.ModelServicesImpl;
import by.itacademy.keikom.taxi.services.impl.UserServicesImpl;

public class CarServicesTest extends AbstractServicesTest {

	private static final Logger LOGGER = LoggerFactory.getLogger(CarServicesTest.class);
	private static CarServicesImpl services = CarServicesImpl.getInstance();
	private List<Car> list = new ArrayList<Car>();

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
	}

	@AfterClass
	public static void cleanTestData() {

		brandServices.delete(brand.getId());
		modelServices.delete(model.getId());
		legalEntityServices.delete(legalEntity.getId());
		userServisec.delete(user.getId());

	}

	@Test
	public void testGRUD() {

		Car car = null;

		try {
			services.save(car);
			Assert.fail();
		} catch (Exception e) {
			LOGGER.error("you cannot save the object entered all of the data");
		}

		car = createCar(user, model, legalEntity);
		services.save(car);
		Assert.assertNotNull(services.getById(car.getId()));

		Car car_1 = services.getById(car.getId());
		Assert.assertEquals(car_1, car);

		car.setReleaseYear(2010);
		services.save(car);
		Assert.assertNotNull(services.getById(car.getId()));

		Car car_2 = services.getById(car.getId());
		Assert.assertEquals(car, car_2);

		list = services.getAll();
		Assert.assertNotNull(list);

		services.delete(car.getId());

		Assert.assertNull(services.getById(car.getId()).getCreated());
		Assert.assertNull(services.getById(car.getId()).getModified());
	}
}
