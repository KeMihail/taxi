package by.itacademy.keikom.taxi.services;

import java.text.ParseException;
import java.util.List;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import by.itacademy.keikom.taxi.dao.dbmodel.Authentication;
import by.itacademy.keikom.taxi.dao.dbmodel.User;
import by.itacademy.keikom.taxi.services.impl.AuthenticationServicesImpl;
import by.itacademy.keikom.taxi.services.impl.UserServicesImpl;

public class AuthenticationServicesTest extends AbstractServicesTest {

	private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationServicesTest.class);

	private static AuthenticationServicesImpl services = AuthenticationServicesImpl.getInstance();
	private List<Authentication> list;

	private static UserServicesImpl userServices = UserServicesImpl.getInstance();
	private static User user;

	@BeforeClass
	public static void prepareTestData() throws ParseException {
		user = createUser();
		userServices.save(user);
	}

	@AfterClass
	public static void cleanTestData() {
		userServices.delete(user.getId());
	}

	@Test
	public void testGRUD() {

		Authentication authentication = null;

		try {
			services.save(authentication);
			Assert.fail();
		} catch (Exception e) {
			LOGGER.error("you cannot save the object entered all of the data");
		}

		authentication = createAuthentication(user);
		services.save(authentication);
		Assert.assertNotNull(services.getById(authentication.getUserId()));

		Authentication authentication1 = services.getById(authentication.getUserId());
		Assert.assertEquals(authentication1.getUserId(), authentication.getUserId());
		Assert.assertEquals(authentication1.getLogin(), authentication.getLogin());
		Assert.assertEquals(authentication1.getPassword(), authentication.getPassword());
		Assert.assertEquals(authentication1.getCreated(), authentication.getCreated());
		Assert.assertEquals(authentication1.getModified(), authentication.getModified());

		authentication.setLogin("login_update");
		services.save(authentication);
		Assert.assertNotNull(services.getById(authentication.getUserId()));

		Authentication authentication2 = services.getById(authentication.getUserId());
		Assert.assertEquals(authentication2.getUserId(), authentication.getUserId());
		Assert.assertEquals(authentication2.getLogin(), authentication.getLogin());
		Assert.assertEquals(authentication2.getPassword(), authentication.getPassword());
		Assert.assertEquals(authentication2.getCreated(), authentication.getCreated());
		Assert.assertEquals(authentication2.getModified(), authentication.getModified());

		list = services.getAll();
		Assert.assertNotNull(list);

		services.delete(authentication.getUserId());
		Assert.assertNull(services.getById(authentication.getUserId()));
	}
}
