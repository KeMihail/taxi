package by.itacademy.keikom.taxi.dao;

import java.util.List;

import by.itacademy.keikom.taxi.dao.dbmodel.LegalEntity;

public interface ILegalEntity {

	Integer create(LegalEntity obj);

	void delete(Integer id);

	void update(LegalEntity obj);

	LegalEntity getById(Integer id);

	List<LegalEntity> getAll();
}
