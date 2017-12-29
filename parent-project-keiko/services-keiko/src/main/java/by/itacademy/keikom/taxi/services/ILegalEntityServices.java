package by.itacademy.keikom.taxi.services;

import java.util.List;

import by.itacademy.keikom.taxi.dao.dbmodel.LegalEntity;

public interface ILegalEntityServices {

	LegalEntity save(LegalEntity obj);

	void delete(Integer id);

	LegalEntity getById(Integer id);

	List<LegalEntity> getAll();
}
