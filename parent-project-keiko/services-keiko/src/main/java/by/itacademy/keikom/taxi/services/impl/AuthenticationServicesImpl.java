package by.itacademy.keikom.taxi.services.impl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import by.itacademy.keikom.taxi.dao.IAuthenticationDao;
import by.itacademy.keikom.taxi.dao.dbmodel.Authentication;
import by.itacademy.keikom.taxi.services.IAuthenticationServices;

@Service
public class AuthenticationServicesImpl implements IAuthenticationServices {

	@Autowired
	private IAuthenticationDao dao;

	@Override
	public Authentication save(Authentication authentication) {

		authentication.setModified(new Timestamp(new Date().getTime()));

		if (authentication.getCreated() != null) {
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
