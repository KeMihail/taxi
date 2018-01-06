package by.itacademy.keikom.taxi.services;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import by.itacademy.keikom.taxi.dao.dbmodel.Brand;
import by.itacademy.keikom.taxi.services.impl.BrandServicesImpl;

public class BrandServicesTest extends AbstractServicesTest {

	private static BrandServicesImpl services = BrandServicesImpl.getInstance();
	private static final Logger LOGGER = LoggerFactory.getLogger(LegalEntityServicesTest.class);
	private List<Brand> list = new ArrayList<Brand>();

	@Test
	public void testGRUD() {
		Brand brand = new Brand();

		try {
			services.save(brand);
			Assert.fail();
		} catch (Exception e) {
			LOGGER.error("you cannot save the object entered all of the data");
		}

		brand = createBrand();
		services.save(brand);
		Brand brand1 = services.getById(brand.getId());
		Assert.assertNotNull(brand1);

		Brand brand2 = services.getById(brand.getId());
		Assert.assertEquals(brand.getId(), brand2.getId());
		Assert.assertEquals(brand.getName(), brand2.getName());
		Assert.assertEquals(brand.getCreated(), brand2.getCreated());
		Assert.assertEquals(brand.getModified(), brand2.getModified());

		brand.setName("Ауди");
		services.save(brand);
		Brand brand3 = services.getById(brand.getId());
		Assert.assertEquals(brand.getId(), brand3.getId());
		Assert.assertEquals(brand.getName(), brand3.getName());
		Assert.assertEquals(brand.getCreated(), brand3.getCreated());
		Assert.assertEquals(brand.getModified(), brand3.getModified());

		list = services.getAll();
		Assert.assertNotNull(list);

		services.delete(brand.getId());
		Assert.assertNull(services.getById(brand.getId()));
	}
}
