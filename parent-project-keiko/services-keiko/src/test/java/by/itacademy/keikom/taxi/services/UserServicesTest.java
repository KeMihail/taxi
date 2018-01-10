package by.itacademy.keikom.taxi.services;

import java.text.ParseException;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import by.itacademy.keikom.taxi.dao.dbmodel.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:context.xml")
public class UserServicesTest extends AbstractServicesTest {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserServicesTest.class);
	@Autowired
	private IUserServices services;

	private List<User> list;

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

		User user1 = services.getById(user.getId());
		Assert.assertEquals(user.getId(), user1.getId());
		Assert.assertEquals(user.getAddress(), user1.getAddress());
		Assert.assertEquals(user.getEmail(), user1.getEmail());
		Assert.assertEquals(user.getLastName(), user1.getLastName());
		Assert.assertEquals(user.getName(), user1.getName());
		Assert.assertEquals(user.getPhoneNumber(), user1.getPhoneNumber());
		Assert.assertEquals(user.getBirthday(), user1.getBirthday());
		Assert.assertEquals(user.getRole(), user1.getRole());
		Assert.assertEquals(user.getDeleted(), user1.getDeleted());
		Assert.assertEquals(user.getCreated(), user1.getCreated());
		Assert.assertEquals(user.getModified(), user1.getModified());

		user.setName("Оля");
		services.save(user);
		Assert.assertNotNull(services.getById(user.getId()));

		User user2 = services.getById(user.getId());
		Assert.assertEquals(user2.getId(), user.getId());
		Assert.assertEquals(user2.getAddress(), user.getAddress());
		Assert.assertEquals(user2.getEmail(), user.getEmail());
		Assert.assertEquals(user2.getLastName(), user.getLastName());
		Assert.assertEquals(user2.getName(), user.getName());
		Assert.assertEquals(user2.getPhoneNumber(), user.getPhoneNumber());
		Assert.assertEquals(user2.getBirthday(), user.getBirthday());
		Assert.assertEquals(user2.getRole(), user.getRole());
		Assert.assertEquals(user2.getDeleted(), user.getDeleted());
		Assert.assertEquals(user2.getCreated(), user.getCreated());
		Assert.assertEquals(user2.getModified(), user.getModified());

		list = services.getAll();
		Assert.assertNotNull(list);

		services.delete(user.getId());
		Assert.assertNull(services.getById(user.getId()));
	}
}