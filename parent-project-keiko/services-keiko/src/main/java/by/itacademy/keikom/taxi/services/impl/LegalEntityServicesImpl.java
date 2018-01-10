package by.itacademy.keikom.taxi.services.impl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import by.itacademy.keikom.taxi.dao.ILegalEntityDao;
import by.itacademy.keikom.taxi.dao.dbmodel.LegalEntity;
import by.itacademy.keikom.taxi.services.ILegalEntityServices;
import by.itacademy.keikom.taxi.services.exeption.NotValidPhoneNumberException;

@Service
public class LegalEntityServicesImpl extends AbstractServicesImpl implements ILegalEntityServices {

	@Autowired
	private ILegalEntityDao dao;

	@Override
	public LegalEntity save(LegalEntity legalEntity) {

		if (!validateEmailAddress(legalEntity.getEmail())) {
			legalEntity.setEmail(null);
		}

		if (!validatePhoneNumber(legalEntity.getPhone_number())) {
			throw new NotValidPhoneNumberException();
		}

		legalEntity.setModified(new Timestamp(new Date().getTime()));
		if (legalEntity.getId() != null) {
			dao.update(legalEntity);
		} else {
			legalEntity.setCreated(new Timestamp(new Date().getTime()));
			Integer id = dao.create(legalEntity);
			legalEntity.setId(id);
		}
		return legalEntity;
	}

	@Override
	public void delete(Integer id) {
		dao.delete(id);
	}

	@Override
	public LegalEntity getById(Integer id) {
		return dao.getById(id);
	}

	@Override
	public List<LegalEntity> getAll() {
		return dao.getAll();
	}
}
