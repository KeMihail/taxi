package by.itacademy.keikom.taxi.dao.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import by.itacademy.keikom.taxi.dao.IAuthenticationDao;
import by.itacademy.keikom.taxi.dao.dbmodel.Authentication;

public class AuthenticationDaoImpl extends AbstractDaoImpl implements IAuthenticationDao {

	private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationDaoImpl.class);
	private static AuthenticationDaoImpl instance = null;

	private AuthenticationDaoImpl() {
	}

	public static synchronized AuthenticationDaoImpl getInstance() {
		if (instance == null) {
			instance = new AuthenticationDaoImpl();
		}
		return instance;
	}

	@Override
	public Integer create(Authentication authentication) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Authentication authentication) {
		// TODO Auto-generated method stub

	}

	@Override
	public Authentication getById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Authentication> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
