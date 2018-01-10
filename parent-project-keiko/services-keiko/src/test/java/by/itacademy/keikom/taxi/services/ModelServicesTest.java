package by.itacademy.keikom.taxi.services;

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
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import by.itacademy.keikom.taxi.dao.dbmodel.Brand;
import by.itacademy.keikom.taxi.dao.dbmodel.Model;
import by.itacademy.keikom.taxi.services.impl.BrandServicesImpl;
import by.itacademy.keikom.taxi.services.impl.ModelServicesImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:context.xml")
public class ModelServicesTest extends AbstractServicesTest {

	private static final Logger LOGGER = LoggerFactory.getLogger(ModelServicesTest.class);

	private static Brand brand;
	@Autowired
	private IBrandServices brandServices;

	@Autowired
	private IModelServices services;
	private static List<Model> list;

	@PostConstruct
	public void prepareTestData() {
		LOGGER.info("prepare data for ModelServicesTest");

		brand = createBrand();
		brandServices.save(brand);
	}

	@PreDestroy
	public void cleanTestData() {
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

		Model model1 = services.getById(model.getId());
		Assert.assertNotNull(model1);
		Assert.assertEquals(model1.getId(), model.getId());
		Assert.assertEquals(model1.getName(), model.getName());
		Assert.assertEquals(model1.getBodyType(), model.getBodyType());
		Assert.assertEquals(model1.getCarCit(), model.getCarCit());
		Assert.assertEquals(model1.getEngineType(), model.getEngineType());
		Assert.assertEquals(model1.getBrandId(), model.getBrandId());
		Assert.assertEquals(model1.getCreated(), model.getCreated());
		Assert.assertEquals(model1.getModified(), model.getModified());

		model.setName("Опель");
		services.save(model);
		Assert.assertNotNull(services.getById(model.getId()));

		Model model2 = services.getById(model.getId());
		Assert.assertEquals(model2.getId(), model.getId());
		Assert.assertEquals(model2.getName(), model.getName());
		Assert.assertEquals(model2.getBodyType(), model.getBodyType());
		Assert.assertEquals(model2.getCarCit(), model.getCarCit());
		Assert.assertEquals(model2.getEngineType(), model.getEngineType());
		Assert.assertEquals(model2.getBrandId(), model.getBrandId());
		Assert.assertEquals(model2.getCreated(), model.getCreated());
		Assert.assertEquals(model2.getModified(), model.getModified());

		list = services.getAll();
		Assert.assertNotNull(list);

		services.delete(model.getId());
		Assert.assertNull(services.getById(model.getId()));
	}
}
