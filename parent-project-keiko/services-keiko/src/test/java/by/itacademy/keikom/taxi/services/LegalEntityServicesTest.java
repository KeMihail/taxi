package by.itacademy.keikom.taxi.services;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import by.itacademy.keikom.taxi.dao.dbmodel.LegalEntity;
import by.itacademy.keikom.taxi.services.impl.LegalEntityServicesImpl;

public class LegalEntityServicesTest extends AbstractServicesTest {

	private static final Logger LOGGER = LoggerFactory.getLogger(LegalEntityServicesTest.class);
	private static LegalEntityServicesImpl services = LegalEntityServicesImpl.getInstance();
	private List<LegalEntity> list = null;

	@Test
	public void testGRUD() {

		LegalEntity legalEntity = null;

		try {
			services.save(legalEntity);
			Assert.fail();
		} catch (Exception e) {
			LOGGER.error("you cannot save the object entered all of the data");
		}

		legalEntity = createLegalEntity();
		services.save(legalEntity);
		Assert.assertNotNull(services.getById(legalEntity.getId()));

		LegalEntity obj_1 = services.getById(legalEntity.getId());
		Assert.assertEquals(obj_1, legalEntity);

		legalEntity.setName("OAO Такси");
		services.save(legalEntity);
		Assert.assertNotNull(services.getById(legalEntity.getId()));

		LegalEntity obj_3 = services.getById(legalEntity.getId());
		Assert.assertEquals(obj_3, legalEntity);

		list = services.getAll();
		Assert.assertNotNull(list);

		services.delete(legalEntity.getId());
		Assert.assertNull(services.getById(legalEntity.getId()).getName());
		Assert.assertNull(services.getById(legalEntity.getId()).getAddress());
		Assert.assertNull(services.getById(legalEntity.getId()).getEmail());
		Assert.assertNull(services.getById(legalEntity.getId()).getPhone_number());
		Assert.assertNull(services.getById(legalEntity.getId()).getCreated());
		Assert.assertNull(services.getById(legalEntity.getId()).getModified());
	}
}
