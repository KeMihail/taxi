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

import by.itacademy.keikom.taxi.dao.IBrandDao;
import by.itacademy.keikom.taxi.dao.dbmodel.Brand;
import by.itacademy.keikom.taxi.dao.exeption.SQLExecutionException;

public class BrandDaoImpl extends AbstractDaoImpl implements IBrandDao {

	private static final Logger LOGGER = LoggerFactory.getLogger(BrandDaoImpl.class);
	private static BrandDaoImpl instance = null;

	private BrandDaoImpl() {
	}

	public static synchronized BrandDaoImpl getInstance() {
		if (instance == null) {
			instance = new BrandDaoImpl();
		}
		return instance;
	}

	@Override
	public Integer create(Brand brand) {
		LOGGER.debug("Create new Brand");
		try (Connection connect = getConnection();
				PreparedStatement pst = connect.prepareStatement("insert into brand(name,created) values (?,?)",
						Statement.RETURN_GENERATED_KEYS)) {
			pst.setString(1, brand.getName());
			pst.setTimestamp(2, brand.getCreated());
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
		// delete from brand where id = ?;

		LOGGER.debug("Delete Brand");
		try (Connection connect = getConnection();
				PreparedStatement pst = connect.prepareStatement("select brand_delete(?)")) {
			pst.setInt(1, id);
			pst.executeUpdate();
			LOGGER.debug("execute SQL{}", "select brand_delete(?);");
		} catch (SQLException e) {
			LOGGER.error("Error from method delete {}", e.getMessage());
		}
	}

	@Override
	public void update(Brand brand) {
		// update brand set name = ?,modified = ? where id = ?

		LOGGER.debug("Update model");
		try (Connection connect = getConnection();
				PreparedStatement pst = connect.prepareStatement("select brand_update(?,?,?)")) {
			pst.setInt(1, brand.getId());
			pst.setString(2, brand.getName());
			pst.setTimestamp(3, brand.getModified());
			pst.executeUpdate();
		} catch (SQLException e) {
			LOGGER.error("Error from method update {}", e.getMessage());
		}
	}

	@Override
	public Brand getById(Integer id) {
		// select * from brand_getById(?);

		LOGGER.debug("show one Brand");
		try (Connection connect = getConnection();
				PreparedStatement pst = connect.prepareStatement("select * from brand_getById(?)")) {
			pst.setInt(1, id);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				return new Brand(rs.getInt(1), rs.getString(2), rs.getTimestamp(3), rs.getTimestamp(4));
			}
		} catch (SQLException e) {
			LOGGER.error("Error from method getById {}", e.getMessage());
		}
		return null;
	}

	@Override
	public List<Brand> getAll() {
		// select * from brand;

		LOGGER.debug("show all Brands");
		List<Brand> list = new ArrayList<Brand>();
		try (Connection connect = getConnection(); Statement st = connect.createStatement()) {
			ResultSet rs = st.executeQuery("select * from brand_getAll();");
			while (rs.next()) {
				list.add(new Brand(rs.getInt(1), rs.getString(2), rs.getTimestamp(3), rs.getTimestamp(4)));
			}
		} catch (SQLException e) {
			LOGGER.error("Error from method getAll {}", e.getMessage());
		}
		return list;
	}
}
