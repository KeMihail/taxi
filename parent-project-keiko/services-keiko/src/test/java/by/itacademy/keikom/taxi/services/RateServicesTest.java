package by.itacademy.keikom.taxi.services;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import by.itacademy.keikom.taxi.dao.dbmodel.Rate;
import by.itacademy.keikom.taxi.services.impl.RateServicesImpl;

public class RateServicesTest extends AbstractServicesTest {

	private static final Logger LOGGER = LoggerFactory.getLogger(RateServicesTest.class);
	private static final RateServicesImpl services = RateServicesImpl.getInstance();
	private List<Rate> list = new ArrayList<Rate>();

	@Test
	public void testGRUD() {

		Rate rate = null;

		try {
			services.save(rate);
			Assert.fail();
		} catch (Exception e) {
			LOGGER.error("you cannot save the object entered all of the data");
		}
		rate = createRate();
		services.save(rate);
		Assert.assertNotNull(services.getById(rate.getId()));

		rate.setName("Ночной");
		services.save(rate);
		Assert.assertNotNull(services.getById(rate.getId()));

		Rate rate_1 = services.getById(rate.getId());
		Assert.assertEquals(rate_1, rate);

		list = services.getAll();
		Assert.assertNotNull(rate);

		services.delete(rate.getId());

		// fail ?: Assert.assertNull(services.getById(rate.getId()));

		Assert.assertNull(services.getById(rate.getId()).getName());
		Assert.assertNull(services.getById(rate.getId()).getCreated());
		Assert.assertNull(services.getById(rate.getId()).getModified());
	}
}
