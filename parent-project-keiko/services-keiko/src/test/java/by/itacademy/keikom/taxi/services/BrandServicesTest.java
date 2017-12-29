package by.itacademy.keikom.taxi.services;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import by.itacademy.keikom.taxi.dao.dbmodel.Brand;
import by.itacademy.keikom.taxi.services.impl.BrandServicesImpl;

public class BrandServicesTest extends AbstractServicesTest {

	private static BrandServicesImpl services = BrandServicesImpl.getInstance();
	private static final Logger LOGGER = LoggerFactory.getLogger(LegalEntityServicesTest.class);

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
		Brand brand_1 = services.getById(brand.getId());
		Assert.assertNotNull(brand_1);

		// maybe this check is unnecessary:
		Brand brand_2 = services.getById(brand.getId());
		Assert.assertEquals(brand, brand_2);

		brand.setName("Ауди");
		services.save(brand);
		Brand brand_3 = services.getById(brand.getId());
		Assert.assertEquals(brand_3, brand);

		services.delete(brand.getId());

		Assert.assertNull(services.getById(brand.getId()).getName());
		Assert.assertNull(services.getById(brand.getId()).getCreated());
		Assert.assertNull(services.getById(brand.getId()).getModified());
	}
}
