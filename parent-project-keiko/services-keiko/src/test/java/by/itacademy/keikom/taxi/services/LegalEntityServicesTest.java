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
	private List<LegalEntity> list;

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

		LegalEntity legalEntity1 = services.getById(legalEntity.getId());
		Assert.assertEquals(legalEntity1.getId(), legalEntity.getId());
		Assert.assertEquals(legalEntity1.getName(), legalEntity.getName());
		Assert.assertEquals(legalEntity1.getAddress(), legalEntity.getAddress());
		Assert.assertEquals(legalEntity1.getPhone_number(), legalEntity.getPhone_number());
		Assert.assertEquals(legalEntity1.getEmail(), legalEntity.getEmail());
		Assert.assertEquals(legalEntity1.getCreated(), legalEntity.getCreated());
		Assert.assertEquals(legalEntity1.getModified(), legalEntity.getModified());

		legalEntity.setName("OAO Такси");
		services.save(legalEntity);
		Assert.assertNotNull(services.getById(legalEntity.getId()));

		LegalEntity legalEntity2 = services.getById(legalEntity.getId());
		Assert.assertEquals(legalEntity2.getId(), legalEntity.getId());
		Assert.assertEquals(legalEntity2.getName(), legalEntity.getName());
		Assert.assertEquals(legalEntity2.getAddress(), legalEntity.getAddress());
		Assert.assertEquals(legalEntity2.getPhone_number(), legalEntity.getPhone_number());
		Assert.assertEquals(legalEntity2.getEmail(), legalEntity.getEmail());
		Assert.assertEquals(legalEntity2.getCreated(), legalEntity.getCreated());
		Assert.assertEquals(legalEntity2.getModified(), legalEntity.getModified());

		list = services.getAll();
		Assert.assertNotNull(list);

		services.delete(legalEntity.getId());
		Assert.assertNull(services.getById(legalEntity.getId()));
	}
}
