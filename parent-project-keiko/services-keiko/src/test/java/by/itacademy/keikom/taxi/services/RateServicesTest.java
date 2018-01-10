package by.itacademy.keikom.taxi.services;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import by.itacademy.keikom.taxi.dao.dbmodel.Rate;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:context.xml")
public class RateServicesTest extends AbstractServicesTest {

	private static final Logger LOGGER = LoggerFactory.getLogger(RateServicesTest.class);

	@Autowired
	private IRateServices services;

	private List<Rate> list = null;

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

		Rate rate1 = services.getById(rate.getId());
		Assert.assertEquals(rate1.getId(), rate.getId());
		Assert.assertEquals(rate1.getName(), rate.getName());
		Assert.assertEquals(rate1.getPriceLanding(), rate.getPriceLanding());
		Assert.assertEquals(rate1.getPriceKilometr(), rate.getPriceKilometr());
		Assert.assertEquals(rate1.getPriceMinuteWait(), rate.getPriceMinuteWait());
		Assert.assertEquals(rate1.getCreated(), rate.getCreated());
		Assert.assertEquals(rate1.getModified(), rate.getModified());

		rate.setName("Ночной");
		services.save(rate);
		Assert.assertNotNull(services.getById(rate.getId()));

		Rate rate2 = services.getById(rate.getId());
		Assert.assertEquals(rate2.getId(), rate.getId());
		Assert.assertEquals(rate2.getName(), rate.getName());
		Assert.assertEquals(rate2.getPriceLanding(), rate.getPriceLanding());
		Assert.assertEquals(rate2.getPriceKilometr(), rate.getPriceKilometr());
		Assert.assertEquals(rate2.getPriceMinuteWait(), rate.getPriceMinuteWait());
		Assert.assertEquals(rate2.getCreated(), rate.getCreated());
		Assert.assertEquals(rate2.getModified(), rate.getModified());

		list = services.getAll();
		Assert.assertNotNull(list);

		services.delete(rate.getId());

		Assert.assertNull(services.getById(rate.getId()));
	}
}
