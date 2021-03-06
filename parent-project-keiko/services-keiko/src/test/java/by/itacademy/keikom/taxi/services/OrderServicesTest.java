package by.itacademy.keikom.taxi.services;

import java.text.ParseException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

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

	@Autowired
	private IOrderServices services;
	private List<Order> list;

	private static Brand brand;
	@Autowired
	private IBrandServices brandServices;

	private static Model model = new Model();
	@Autowired
	private IModelServices modelServices;

	private static LegalEntity legalEntity;
	@Autowired
	private ILegalEntityServices legalEntityServices;

	private static Car car;
	@Autowired
	private ICarServices carServices;

	private static Rate rate;
	@Autowired
	private IRateServices rateServices;

	private static User userDriver;
	@Autowired
	private IUserServices userServisec;

	private static User userClient;

	@PostConstruct
	public void prepareTestData() throws ParseException {

		brand = createBrand();
		brandServices.save(brand);

		model = createModel(brand);
		modelServices.save(model);

		legalEntity = createLegalEntity();
		legalEntityServices.save(legalEntity);

		userDriver = createUser();
		userServisec.save(userDriver);

		userClient = createUserClient();
		userServisec.save(userClient);

		car = createCar(userDriver, model, legalEntity);
		carServices.save(car);

		rate = createRate();
		rateServices.save(rate);
	}

	@PreDestroy
	public void cleanTestData() {

		brandServices.delete(brand.getId());
		modelServices.delete(model.getId());
		legalEntityServices.delete(legalEntity.getId());
		userServisec.delete(userDriver.getId());
		userServisec.delete(userClient.getId());
		carServices.delete(car.getId());
		rateServices.delete(rate.getId());
	}

	@Test
	public void testGRUD() {

		Order order = null;

		try {
			services.save(order);
			Assert.fail();
		} catch (Exception e) {
			LOGGER.error("you cannot save the object entered all of the data");
		}

		order = createOrder(car, rate, userClient);
		services.save(order);
		Assert.assertNotNull(services.getById(order.getId()));

		Order order1 = services.getById(order.getId());
		Assert.assertEquals(order1.getId(), order.getId());
		Assert.assertEquals(order1.getCarId(), order.getCarId());
		Assert.assertEquals(order1.getUserId(), order.getUserId());
		Assert.assertEquals(order1.getDepartureAddress(), order.getDepartureAddress());
		Assert.assertEquals(order1.getArrivalAddress(), order.getArrivalAddress());
		Assert.assertEquals(order1.getOrderBegin(), order.getOrderBegin());
		Assert.assertEquals(order1.getOrderEnd(), order.getOrderEnd());
		Assert.assertEquals(order1.getDistanceOrder(), order.getDistanceOrder());
		Assert.assertEquals(order1.getDistancePaid(), order.getDistancePaid());
		Assert.assertEquals(order1.getInactivityMinutes(), order.getInactivityMinutes());
		Assert.assertEquals(order1.getRateId(), order.getRateId());
		Assert.assertEquals(order1.getDeleted(), order.getDeleted());
		Assert.assertEquals(order1.getCreated(), order.getCreated());
		Assert.assertEquals(order1.getModified(), order.getModified());

		order.setDeleted(true);
		services.save(order);
		Assert.assertNotNull(services.getById(order.getId()));

		Order order2 = services.getById(order.getId());
		Assert.assertEquals(order2.getId(), order.getId());
		Assert.assertEquals(order2.getCarId(), order.getCarId());
		Assert.assertEquals(order2.getUserId(), order.getUserId());
		Assert.assertEquals(order2.getDepartureAddress(), order.getDepartureAddress());
		Assert.assertEquals(order2.getArrivalAddress(), order.getArrivalAddress());
		Assert.assertEquals(order2.getOrderBegin(), order.getOrderBegin());
		Assert.assertEquals(order2.getOrderEnd(), order.getOrderEnd());
		Assert.assertEquals(order2.getDistanceOrder(), order.getDistanceOrder());
		Assert.assertEquals(order2.getDistancePaid(), order.getDistancePaid());
		Assert.assertEquals(order2.getInactivityMinutes(), order.getInactivityMinutes());
		Assert.assertEquals(order2.getRateId(), order.getRateId());
		Assert.assertEquals(order2.getDeleted(), order.getDeleted());
		Assert.assertEquals(order2.getCreated(), order.getCreated());
		Assert.assertEquals(order2.getModified(), order.getModified());

		list = services.getAll();
		Assert.assertNotNull(list);

		services.delete(order.getId());
		Assert.assertNull(services.getById(order.getId()));

	}
}
