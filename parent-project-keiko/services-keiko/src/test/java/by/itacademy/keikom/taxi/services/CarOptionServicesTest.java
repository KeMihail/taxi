package by.itacademy.keikom.taxi.services;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import by.itacademy.keikom.taxi.dao.dbmodel.CarOption;
import by.itacademy.keikom.taxi.services.impl.CarOptionServicesImpl;

public class CarOptionServicesTest extends AbstractServicesTest {

	private static final CarOptionServicesImpl services = CarOptionServicesImpl.getInstance();
	private static final Logger LOGGER = LoggerFactory.getLogger(CarOptionServicesTest.class);
	private List<CarOption> list = new ArrayList<CarOption>();

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
		Assert.assertNotNull(carOption);

		carOption.setName("Коженный салон");
		services.save(carOption);
		Assert.assertNotNull(carOption);

		CarOption carOption_1 = services.getById(carOption.getId());
		Assert.assertEquals(carOption_1, carOption);

		list = services.getAll();
		Assert.assertNotNull(list);

		services.delete(carOption.getId());
		Assert.assertNull(services.getById(carOption.getId()).getName());
		Assert.assertNull(services.getById(carOption.getId()).getCreated());
		Assert.assertNull(services.getById(carOption.getId()).getModified());
	}

}
