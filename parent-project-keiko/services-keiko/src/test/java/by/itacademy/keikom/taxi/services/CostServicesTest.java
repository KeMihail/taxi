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
import by.itacademy.keikom.taxi.dao.dbmodel.Costs;
import by.itacademy.keikom.taxi.dao.dbmodel.LegalEntity;
import by.itacademy.keikom.taxi.dao.dbmodel.Model;
import by.itacademy.keikom.taxi.dao.dbmodel.User;
import by.itacademy.keikom.taxi.services.impl.CostsServicesImpl;

public class CostServicesTest extends AbstractServicesTest {

	private static final Logger LOGGER = LoggerFactory.getLogger(CostServicesTest.class);

	@Autowired
	private CostsServicesImpl services;
	private List<Costs> list;

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

	private static User userDriver;
	@Autowired
	private static IUserServices userServisec;

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

		car = createCar(userDriver, model, legalEntity);
		carServices.save(car);
	}

	@PreDestroy
	public void cleanTestData() {

		brandServices.delete(brand.getId());
		modelServices.delete(model.getId());
		legalEntityServices.delete(legalEntity.getId());
		userServisec.delete(userDriver.getId());
		carServices.delete(car.getId());
	}

	@Test
	public void testGRUD() {

		Costs costs = null;
		try {
			services.save(costs);
			Assert.fail();
		} catch (Exception e) {
			LOGGER.error("you cannot save the object entered all of the data");
		}
		costs = createCosts(car);
		services.save(costs);
		Assert.assertNotNull(services.getById(costs.getCarId()));

		Costs costs1 = services.getById(costs.getCarId());
		Assert.assertEquals(costs1.getCarId(), costs.getCarId());
		Assert.assertEquals(costs1.getCarService(), costs.getCarService());
		Assert.assertEquals(costs1.getFuelConsumption(), costs.getFuelConsumption());
		Assert.assertEquals(costs1.getInsurance(), costs.getInsurance());
		Assert.assertEquals(costs1.getOther(), costs.getOther());
		Assert.assertEquals(costs1.getPretripInspection(), costs.getPretripInspection());
		Assert.assertEquals(costs1.getSalaryDriver(), costs.getSalaryDriver());
		Assert.assertEquals(costs1.getTaxes(), costs.getTaxes());
		Assert.assertEquals(costs1.getTechnicalInspection(), costs.getTechnicalInspection());
		Assert.assertEquals(costs1.getCreated(), costs.getCreated());
		Assert.assertEquals(costs1.getModified(), costs.getModified());

		costs.setCarService(300.0);
		services.save(costs);
		Assert.assertNotNull(costs);

		Costs costs2 = services.getById(costs.getCarId());
		Assert.assertEquals(costs2.getCarId(), costs.getCarId());
		Assert.assertEquals(costs2.getCarService(), costs.getCarService());
		Assert.assertEquals(costs2.getFuelConsumption(), costs.getFuelConsumption());
		Assert.assertEquals(costs2.getInsurance(), costs.getInsurance());
		Assert.assertEquals(costs2.getOther(), costs.getOther());
		Assert.assertEquals(costs2.getPretripInspection(), costs.getPretripInspection());
		Assert.assertEquals(costs2.getSalaryDriver(), costs.getSalaryDriver());
		Assert.assertEquals(costs2.getTaxes(), costs.getTaxes());
		Assert.assertEquals(costs2.getTechnicalInspection(), costs.getTechnicalInspection());
		Assert.assertEquals(costs2.getCreated(), costs.getCreated());
		Assert.assertEquals(costs2.getModified(), costs.getModified());

		list = services.getAll();
		Assert.assertNotNull(list);

		services.delete(costs.getCarId());
		Assert.assertNull(services.getById(costs.getCarId()));
	}
}
