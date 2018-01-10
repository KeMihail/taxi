package by.itacademy.keikom.taxi.services.impl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import by.itacademy.keikom.taxi.dao.IUserDao;
import by.itacademy.keikom.taxi.dao.dbmodel.User;
import by.itacademy.keikom.taxi.services.IUserServices;
import by.itacademy.keikom.taxi.services.exeption.NotValidBirthdayException;
import by.itacademy.keikom.taxi.services.exeption.NotValidPhoneNumberException;

@Service
public class UserServicesImpl extends AbstractServicesImpl implements IUserServices {

	@Autowired
	private IUserDao dao;

	@Override
	public User save(User user) {

		if (!validateEmailAddress(user.getEmail())) {
			user.setEmail(null);
		}

		if (!validatePhoneNumber(user.getPhoneNumber())) {
			throw new NotValidPhoneNumberException();
		}

		if (!validateBirthday(user.getBirthday().toString())) {
			throw new NotValidBirthdayException();
		}

		user.setModified(new Timestamp(new Date().getTime()));

		if (user.getId() != null) {
			dao.update(user);
		} else {
			user.setCreated(new Timestamp(new Date().getTime()));
			Integer id = dao.create(user);
			user.setId(id);
			;
		}
		return user;
	}

	@Override
	public void delete(Integer id) {
		dao.delete(id);
	}

	@Override
	public User getById(Integer id) {
		return dao.getById(id);
	}

	@Override
	public List<User> getAll() {
		return dao.getAll();
	}
}
