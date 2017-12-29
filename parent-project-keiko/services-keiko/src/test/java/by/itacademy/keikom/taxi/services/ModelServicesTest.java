package by.itacademy.keikom.taxi.services;

import java.util.ArrayList;
import java.util.List;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import by.itacademy.keikom.taxi.dao.dbmodel.Brand;
import by.itacademy.keikom.taxi.dao.dbmodel.Model;
import by.itacademy.keikom.taxi.dao.enums.EBodyType;
import by.itacademy.keikom.taxi.dao.enums.ECarKit;
import by.itacademy.keikom.taxi.dao.enums.EEngineType;
import by.itacademy.keikom.taxi.services.impl.BrandServicesImpl;
import by.itacademy.keikom.taxi.services.impl.ModelServicesImpl;

public class ModelServicesTest extends AbstractServicesTest {

	private static BrandServicesImpl brandServices = BrandServicesImpl.getInstance();
	private static ModelServicesImpl services = ModelServicesImpl.getInstance();
	private static Brand brand;
	private static final Logger LOGGER = LoggerFactory.getLogger(ModelServicesTest.class);
	private static List<Model> list = null;

	@BeforeClass
	public static void prepareTestData() {
		LOGGER.info("prepare data for ModelServicesTest");
		brand = createBrand();
		brandServices.save(brand);
		list = new ArrayList<Model>();
	}

	@AfterClass
	public static void cleanTestData() {
		brandServices.delete(brand.getId());
	}

	@Test
	public void testGRUD() {
		Model model = null;

		try {
			services.save(model);
			Assert.fail();
		} catch (Exception e) {
			LOGGER.error("you cannot save the object entered all of the data");
		}

		model = createModel(brand);
		services.save(model);
		Assert.assertNotNull(model);

		Model model_1 = services.getById(model.getId());
		Assert.assertNotNull(model_1);
		Assert.assertEquals(model_1, model);

		model.setName("Опель");
		services.save(model);
		Assert.assertNotNull(services.getById(model.getId()));

		Model model_2 = services.getById(model.getId());
		Assert.assertEquals(model_2, model);

		list = services.getAll();
		Assert.assertNotNull(list);

		// services.delete(model.getId());
		// Assert.assertNull(services.getById(model.getId()));
	}
}
