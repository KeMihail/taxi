package by.itacademy.keikom.taxi.services;

import java.text.ParseException;

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
import by.itacademy.keikom.taxi.dao.dbmodel.Order;
import by.itacademy.keikom.taxi.dao.dbmodel.Rate;
import by.itacademy.keikom.taxi.dao.dbmodel.User;
import by.itacademy.keikom.taxi.services.impl.BrandServicesImpl;
import by.itacademy.keikom.taxi.services.impl.CarServicesImpl;
import by.itacademy.keikom.taxi.services.impl.LegalEntityServicesImpl;
import by.itacademy.keikom.taxi.services.impl.ModelServicesImpl;
import by.itacademy.keikom.taxi.services.impl.OrderServicesImpl;
import by.itacademy.keikom.taxi.services.impl.RateServicesImpl;
import by.itacademy.keikom.taxi.services.impl.UserServicesImpl;

public class OrderServicesTest extends AbstractServicesTest {

	private static final Logger LOGGER = LoggerFactory.getLogger(OrderServicesTest.class);
	private static final OrderServicesImpl srvices = OrderServicesImpl.getInstance();

	private static CarServicesImpl carServices = CarServicesImpl.getInstance();
	private static Car car;

	private static Model model = new Model();
	private static ModelServicesImpl modelServices = ModelServicesImpl.getInstance();

	private static LegalEntity legalEntity;
	private static LegalEntityServicesImpl legalEntityServices = LegalEntityServicesImpl.getInstance();

	private static Brand brand;
	private static BrandServicesImpl brandServices = BrandServicesImpl.getInstance();

	private static User user;
	private static UserServicesImpl userServisec = UserServicesImpl.getInstance();

	private static User userClient;

	private static Rate rate;
	private static RateServicesImpl rateServices = RateServicesImpl.getInstance();

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
		userClient = createUserClient();
		userServisec.save(userClient);

		car = createCar(user, model, legalEntity);
		carServices.save(car);

		rate = createRate();
		rateServices.save(rate);
	}

	@AfterClass
	public static void cleanTestData() {

		brandServices.delete(brand.getId());
		modelServices.delete(model.getId());
		legalEntityServices.delete(legalEntity.getId());
		userServisec.delete(user.getId());
		userServisec.delete(userClient.getId());
		carServices.delete(car.getId());
		rateServices.delete(rate.getId());
	}

	@Test
	public void testGRUD() {

		Order order = null;

		try {
			srvices.save(order);
			Assert.fail();
		} catch (Exception e) {
			LOGGER.error("you cannot save the object entered all of the data");
		}
		
		order = createOrder(car, rate, userClient);
		srvices.save(order);
		Assert.assertNotNull(srvices.getById(order.getId()));
	}
}
