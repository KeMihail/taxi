package by.itacademy.keikom.taxi.services;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import by.itacademy.keikom.taxi.dao.dbmodel.CarOption;
import by.itacademy.keikom.taxi.services.impl.CarOptionServicesImpl;

public class CarOptionServicesTest extends AbstractServicesTest {

	private static final Logger LOGGER = LoggerFactory.getLogger(CarOptionServicesTest.class);

	private static final CarOptionServicesImpl services = CarOptionServicesImpl.getInstance();
	private List<CarOption> list;

	@Test
	public void testGRUD() {

		CarOption carOption = null;

		try {
			services.save(carOption);
			Assert.fail();

		} catch (Exception e) {
			LOGGER.error("you cannot save the object entered all of the data");
		}

		carOption = createCarOption();
		services.save(carOption);
		Assert.assertNotNull(services.getById(carOption.getId()));

		CarOption carOption1 = services.getById(carOption.getId());
		Assert.assertEquals(carOption1.getId(), carOption.getId());
		Assert.assertEquals(carOption1.getName(), carOption.getName());
		Assert.assertEquals(carOption1.getCreated(), carOption.getCreated());
		Assert.assertEquals(carOption1.getModified(), carOption.getModified());

		carOption.setName("Коженный салон");
		services.save(carOption);
		Assert.assertNotNull(services.getById(carOption.getId()));

		CarOption carOption2 = services.getById(carOption.getId());
		Assert.assertEquals(carOption2.getId(), carOption.getId());
		Assert.assertEquals(carOption2.getName(), carOption.getName());
		Assert.assertEquals(carOption2.getCreated(), carOption.getCreated());
		Assert.assertEquals(carOption2.getModified(), carOption.getModified());

		list = services.getAll();
		Assert.assertNotNull(list);

		services.delete(carOption.getId());
		Assert.assertNull(services.getById(carOption.getId()));
	}
}
