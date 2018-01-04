package by.itacademy.keikom.taxi.services.impl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import by.itacademy.keikom.taxi.dao.dbmodel.Authentication;
import by.itacademy.keikom.taxi.dao.impl.AuthenticationDaoImpl;
import by.itacademy.keikom.taxi.services.IAuthenticationServices;

public class AuthenticationServicesImpl implements IAuthenticationServices {

	private static AuthenticationDaoImpl dao = AuthenticationDaoImpl.getInstance();
	private static AuthenticationServicesImpl instance;

	private AuthenticationServicesImpl() {
	}

	public static synchronized AuthenticationServicesImpl getInstance() {
		if (instance == null) {
			instance = new AuthenticationServicesImpl();
		}
		return instance;
	}

	@Override
	public Authentication save(Authentication authentication) {

		if (authentication.getCreated() != null) {
			authentication.setModified(new Timestamp(new Date().getTime()));
			dao.update(authentication);
		} else {
			authentication.setCreated(new Timestamp(new Date().getTime()));
			dao.create(authentication);
		}
		return authentication;
	}

	@Override
	public void delete(Integer id) {
		dao.delete(id);
	}

	@Override
	public Authentication getById(Integer id) {
		return dao.getById(id);
	}

	@Override
	public List<Authentication> getAll() {
		return dao.getAll();
	}
}
