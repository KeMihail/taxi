package by.itacademy.keikom.taxi.services;

import java.text.ParseException;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import by.itacademy.keikom.taxi.dao.dbmodel.User;
import by.itacademy.keikom.taxi.services.impl.UserServicesImpl;

public class UserServicesTest extends AbstractServicesTest {

	private static UserServicesImpl services = UserServicesImpl.getInstance();
	private static final Logger LOGGER = LoggerFactory.getLogger(UserServicesTest.class);
	private List<User> list = null;

	@Test
	public void testGRUD() throws ParseException {
		User user = null;

		try {
			services.save(user);
			Assert.fail();
		} catch (Exception e) {
			LOGGER.error("you cannot save the object entered all of the data");
		}

		user = createUser();
		services.save(user);
		Assert.assertNotNull(services.getById(user.getId()));

		user.setName("Оля");
		services.save(user);
		Assert.assertNotNull(services.getById(user.getId()));

		User user_1 = services.getById(user.getId());
		Assert.assertEquals(user_1, user);

		list = services.getAll();
		Assert.assertNotNull(list);

		services.delete(user.getId());
		Assert.assertNull(services.getById(user.getId()).getAddress());
		Assert.assertNull(services.getById(user.getId()).getEmail());
		Assert.assertNull(services.getById(user.getId()).getLastName());
		Assert.assertNull(services.getById(user.getId()).getName());
		Assert.assertNull(services.getById(user.getId()).getPhoneNumber());
		Assert.assertNull(services.getById(user.getId()).getBirthday());
		Assert.assertNull(services.getById(user.getId()).getCreated());
	}
}