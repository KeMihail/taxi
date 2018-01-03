package by.itacademy.keikom.taxi.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import by.itacademy.keikom.taxi.dao.ILegalEntity;
import by.itacademy.keikom.taxi.dao.dbmodel.LegalEntity;
import by.itacademy.keikom.taxi.dao.exeption.SQLExecutionException;

public class LegalEntityDaoImpl extends AbstractDaoImpl implements ILegalEntity {

	private static final Logger LOGGER = LoggerFactory.getLogger(LegalEntityDaoImpl.class);
	private static LegalEntityDaoImpl instance = null;

	private LegalEntityDaoImpl() {
	}

	public synchronized static LegalEntityDaoImpl getInstance() {
		if (instance == null) {
			instance = new LegalEntityDaoImpl();
		}
		return instance;
	}

	@Override
	public Integer create(LegalEntity obj) {

		try (Connection connect = getConnection();
				PreparedStatement pst = connect.prepareStatement(
						"insert into legal_entity(name,address,phone_number,email,created) values (?,?,?,?,?);",
						Statement.RETURN_GENERATED_KEYS)) {
			LOGGER.info("execute SQL: create new LegalEntityr");
			pst.setString(1, obj.getName());
			pst.setString(2, obj.getAddress());
			pst.setString(3, obj.getPhone_number());
			pst.setString(4, obj.getEmail());
			pst.setTimestamp(5, obj.getCreated());
			pst.executeUpdate();

			ResultSet rs = pst.getGeneratedKeys();
			rs.next();
			Integer id = rs.getInt("id");
			rs.close();
			return id;
		} catch (SQLException e) {
			throw new SQLExecutionException(e);
		}
	}

	@Override
	public void delete(Integer id) {
		// delete from legal_entity where id = _id;
		// select legalEntity_delete(?);

		try (Connection connect = getConnection();
				PreparedStatement pst = connect.prepareStatement("select legalEntity_delete(?);")) {
			LOGGER.info("execute SQL: delete one LegalEntity");
			pst.setInt(1, id);
			pst.executeUpdate();
		} catch (SQLException e) {
			LOGGER.error("Error from method delete {}", e.getMessage());
		}
	}

	@Override
	public void update(LegalEntity obj) {
		// update legal_entity set name = ?, address = ?, phone_number = ?, email =
		// ?,modified = ? where id = ?(1);
		// "select legalEntity_update(?,?,?,?,?,?"

		try (Connection connect = getConnection();
				PreparedStatement pst = connect.prepareStatement(
						"update legal_entity set name = ?, address = ?, phone_number = ?, email = ?\r\n"
								+ "	,modified = ? where id = ?;")) {
			LOGGER.info("execute SQL: Update Legal entity");
			pst.setInt(6, obj.getId());
			pst.setString(1, obj.getName());
			pst.setString(2, obj.getAddress());
			pst.setString(3, obj.getPhone_number());
			pst.setString(4, obj.getEmail());
			pst.setTimestamp(5, obj.getModified());
			pst.executeUpdate();
		} catch (SQLException e) {
			LOGGER.error("Error from method update {}", e.getMessage());
		}
	}

	@Override
	public LegalEntity getById(Integer id) {

		try (Connection connect = getConnection();
				PreparedStatement pst = connect.prepareStatement("select * from legalEntity_getById(?);")) {
			LOGGER.info("execute SQL: show one Legal entity");
			pst.setInt(1, id);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				return new LegalEntity(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
						rs.getTimestamp(6), rs.getTimestamp(7));
			}
		} catch (SQLException e) {
			LOGGER.error("Error from method getById {}", e.getMessage());
		}
		return null;
	}

	@Override
	public List<LegalEntity> getAll() {
		// select * from legal_entity;

		List<LegalEntity> list = new ArrayList<LegalEntity>();
		try (Connection connect = getConnection(); Statement st = connect.createStatement()) {
			LOGGER.info("execute SQL: show all Legal entity");
			ResultSet rs = st.executeQuery("select * from legalEntity_getAll();");
			while (rs.next()) {
				list.add(new LegalEntity(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getString(5), rs.getTimestamp(6), rs.getTimestamp(7)));
			}
		} catch (SQLException e) {
			LOGGER.error("Error from method getAll {}", e.getMessage());
		}
		return list;
	}

}
