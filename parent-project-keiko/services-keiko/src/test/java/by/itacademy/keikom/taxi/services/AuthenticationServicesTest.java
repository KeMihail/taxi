package by.itacademy.keikom.taxi.services;

import java.text.ParseException;
import java.util.ArrayList;
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

	private static AuthenticationServicesImpl services = AuthenticationServicesImpl.getInstance();
	private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationServicesTest.class);
	private static UserServicesImpl userServices = UserServicesImpl.getInstance();
	private static User user = new User();
	private List<Authentication> list = new ArrayList<Authentication>();

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

		authentication.setLogin("login_update");
		services.save(authentication);
		Assert.assertNotNull(authentication);

		Authentication authentication_1 = services.getById(authentication.getUserId());
		Assert.assertEquals(authentication_1, authentication);

		list = services.getAll();
		Assert.assertNotNull(list);

		services.delete(authentication.getUserId());
		Assert.assertNull(services.getById(authentication.getUserId()).getLogin());
		Assert.assertNull(services.getById(authentication.getUserId()).getPassword());
		Assert.assertNull(services.getById(authentication.getUserId()).getCreated());
		Assert.assertNull(services.getById(authentication.getUserId()).getModified());
	}
}
