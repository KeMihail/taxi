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
import org.springframework.stereotype.Repository;

import by.itacademy.keikom.taxi.dao.ILegalEntityDao;
import by.itacademy.keikom.taxi.dao.dbmodel.LegalEntity;
import by.itacademy.keikom.taxi.dao.exeption.SQLExecutionException;

@Repository
public class LegalEntityDaoImpl extends AbstractDaoImpl implements ILegalEntityDao {

	private static final Logger LOGGER = LoggerFactory.getLogger(LegalEntityDaoImpl.class);

	@Override
	public Integer create(LegalEntity legalEntity) {

		try (Connection connect = getConnection();
				PreparedStatement pst = connect.prepareStatement(
						"insert into legal_entity(name,address,phone_number,email,created,modified) values (?,?,?,?,?,?);",
						Statement.RETURN_GENERATED_KEYS)) {
			LOGGER.info("execute SQL: create new LegalEntityr");

			pst.setString(1, legalEntity.getName());
			pst.setString(2, legalEntity.getAddress());
			pst.setString(3, legalEntity.getPhone_number());
			pst.setString(4, legalEntity.getEmail());
			pst.setTimestamp(5, legalEntity.getCreated());
			pst.setTimestamp(6, legalEntity.getModified());
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

		try (Connection connect = getConnection();
				PreparedStatement pst = connect.prepareStatement("delete from legal_entity where id = ?")) {
			LOGGER.info("execute SQL: delete one LegalEntity");

			pst.setInt(1, id);
			pst.executeUpdate();
		} catch (SQLException e) {
			LOGGER.error("Error from method delete {}", e.getMessage());
		}
	}

	@Override
	public void update(LegalEntity legalEntity) {

		try (Connection connect = getConnection();
				PreparedStatement pst = connect.prepareStatement(
						"update legal_entity set name = ?, address = ?, phone_number = ?, email = ?\r\n"
								+ "	,modified = ? where id = ?")) {
			LOGGER.info("execute SQL: Update Legal entity");

			pst.setString(1, legalEntity.getName());
			pst.setString(2, legalEntity.getAddress());
			pst.setString(3, legalEntity.getPhone_number());
			pst.setString(4, legalEntity.getEmail());
			pst.setTimestamp(5, legalEntity.getModified());
			pst.setInt(6, legalEntity.getId());
			pst.executeUpdate();
		} catch (SQLException e) {
			LOGGER.error("Error from method update {}", e.getMessage());
		}
	}

	@Override
	public LegalEntity getById(Integer id) {

		try (Connection connect = getConnection();
				PreparedStatement pst = connect.prepareStatement("select * from legal_entity where id = ?")) {
			LOGGER.info("execute SQL: show one Legal Entity");

			pst.setInt(1, id);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				return parseLegalEntity(rs);
			}
		} catch (SQLException e) {
			LOGGER.error("Error from method getById {}", e.getMessage());
		}
		return null;
	}

	@Override
	public List<LegalEntity> getAll() {

		List<LegalEntity> list = new ArrayList<LegalEntity>();

		try (Connection connect = getConnection(); Statement st = connect.createStatement()) {
			LOGGER.info("execute SQL: show all Legal entity");

			ResultSet rs = st.executeQuery("select * from legal_entity");
			while (rs.next()) {
				list.add(parseLegalEntity(rs));
			}
		} catch (SQLException e) {
			LOGGER.error("Error from method getAll {}", e.getMessage());
		}
		return list;
	}

	private LegalEntity parseLegalEntity(ResultSet rs) throws SQLException {
		LegalEntity legalEntity = new LegalEntity();
		legalEntity.setId(rs.getInt(1));
		legalEntity.setName(rs.getString(2));
		legalEntity.setAddress(rs.getString(3));
		legalEntity.setPhoneNumber(rs.getString(4));
		legalEntity.setEmail(rs.getString(5));
		legalEntity.setCreated(rs.getTimestamp(6));
		legalEntity.setModified(rs.getTimestamp(7));
		return legalEntity;
	}
}
