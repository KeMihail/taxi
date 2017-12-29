package by.itacademy.keikom.taxi.services.impl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import by.itacademy.keikom.taxi.dao.dbmodel.LegalEntity;
import by.itacademy.keikom.taxi.dao.impl.LegalEntityDaoImpl;
import by.itacademy.keikom.taxi.services.ILegalEntityServices;

public class LegalEntityServicesImpl implements ILegalEntityServices {
	private static LegalEntityServicesImpl instance = null;
	private static LegalEntityDaoImpl dao = LegalEntityDaoImpl.getInstance();

	private LegalEntityServicesImpl() {
	}

	public static synchronized LegalEntityServicesImpl getInstance() {
		if (instance == null) {
			instance = new LegalEntityServicesImpl();
		}
		return instance;
	}

	@Override
	public LegalEntity save(LegalEntity obj) {
		if (obj.getId() == null) {
			obj.setCreated(new Timestamp(new Date().getTime()));
			Integer id = dao.create(obj);
			obj.setId(id);
		} else {
			obj.setModified(new Timestamp(new Date().getTime()));
			dao.update(obj);
		}
		return obj;
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
