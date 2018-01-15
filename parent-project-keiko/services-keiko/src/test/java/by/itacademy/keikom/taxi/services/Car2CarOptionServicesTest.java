package by.itacademy.keikom.taxi.services;

import java.text.ParseException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import by.itacademy.keikom.taxi.dao.dbmodel.Brand;
import by.itacademy.keikom.taxi.dao.dbmodel.Car;
import by.itacademy.keikom.taxi.dao.dbmodel.Car2CarOption;
import by.itacademy.keikom.taxi.dao.dbmodel.CarOption;
import by.itacademy.keikom.taxi.dao.dbmodel.LegalEntity;
import by.itacademy.keikom.taxi.dao.dbmodel.Model;
import by.itacademy.keikom.taxi.dao.dbmodel.User;

public class Car2CarOptionServicesTest extends AbstractServicesTest {

	private static final Logger LOGGER = LoggerFactory.getLogger(Car2CarOptionServicesTest.class);

	@Autowired
	private ICar2CarOptionServices services;
	private List<Car2CarOption> list;

	@Autowired
	private ICarServices carServices;
	private static Car car;

	@Autowired
	private ICarOptionServices carOptionServices;
	private static CarOption carOption;
	private static CarOption carOptionUpdate;

	@Autowired
	private IModelServices modelServices;
	private static Model model;

	@Autowired
	private ILegalEntityServices legalEntityServices;
	private static LegalEntity legalEntity;

	@Autowired
	private IUserServices userServisec;
	private static User user;

	@Autowired
	private IBrandServices brandServices;
	private static Brand brand;

	private static List<Integer> getByIdOption;
	private static List<Integer> getByIdCar;

	@PostConstruct
	public void prepareTestData() throws ParseException {

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

		carOptionUpdate = createCarOptionUpdate();
		carOptionServices.save(carOptionUpdate);
	}

	@PreDestroy
	public void cleanTestData() {

		brandServices.delete(brand.getId());
		modelServices.delete(model.getId());
		legalEntityServices.delete(legalEntity.getId());
		userServisec.delete(user.getId());
		carServices.delete(car.getId());
		carOptionServices.delete(carOption.getId());
		carOptionServices.delete(carOptionUpdate.getId());
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
		obj = createCar2CarOption(car, carOption);
		services.create(obj);
		Assert.assertNotNull(services.getById(obj));

		Car2CarOption obj1 = services.getById(obj);
		Assert.assertEquals(obj1.getCarId(), obj.getCarId());
		Assert.assertEquals(obj1.getCarOptionId(), obj.getCarOptionId());

		Car2CarOption newObj = createCar2CarOption(car, carOptionUpdate);
		services.update(obj, newObj);
		Assert.assertNotNull(services.getById(newObj));

		Car2CarOption obj2 = services.getById(newObj);
		Assert.assertEquals(newObj.getCarId(), obj2.getCarId());
		Assert.assertEquals(newObj.getCarOptionId(), obj2.getCarOptionId());

		list = services.getAll();
		Assert.assertNotNull(list);

		getByIdOption = services.getByIdOption(obj.getCarId());
		Assert.assertNotNull(getByIdOption);

		getByIdCar = services.getByIdCar(obj.getCarOptionId());
		Assert.assertNotNull(getByIdCar);

		services.delete(obj);
		Assert.assertNull(services.getById(obj));
	}
}
