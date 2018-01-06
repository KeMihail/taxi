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

import by.itacademy.keikom.taxi.dao.IModelDao;
import by.itacademy.keikom.taxi.dao.dbmodel.Model;
import by.itacademy.keikom.taxi.dao.enums.EBodyType;
import by.itacademy.keikom.taxi.dao.enums.ECarKit;
import by.itacademy.keikom.taxi.dao.enums.EEngineType;
import by.itacademy.keikom.taxi.dao.exeption.SQLExecutionException;

public class ModelDaoImpl extends AbstractDaoImpl implements IModelDao {

	private static final Logger LOGGER = LoggerFactory.getLogger(ModelDaoImpl.class);
	private static ModelDaoImpl instance = null;

	private ModelDaoImpl() {
	}

	public static synchronized ModelDaoImpl getInstance() {
		if (instance == null) {
			instance = new ModelDaoImpl();
		}
		return instance;
	}

	@Override
	public Integer create(Model model) {

		try (Connection connect = getConnection();
				PreparedStatement pst = connect
						.prepareStatement("insert into model (name,car_kit,engine_type,body_type,brand_id,created)\r\n"
								+ "values (?,?,?,?,?,?);", Statement.RETURN_GENERATED_KEYS)) {
			LOGGER.info("execute SQL: Create new model");
			pst.setString(1, model.getName());
			pst.setString(2, model.getCarCit().toString());
			pst.setString(3, model.getEngineType().toString());
			pst.setString(4, model.getBodyType().toString());
			pst.setInt(5, model.getBrandId());
			pst.setTimestamp(6, model.getCreated());
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
				PreparedStatement pst = connect.prepareStatement("delete from model where id = ?")) {
			LOGGER.info("execute SQL: Delete model");
			pst.setInt(1, id);
			pst.executeUpdate();
		} catch (SQLException e) {
			LOGGER.error("Error from method delete {}", e.getMessage());
		}
	}

	@Override
	public void update(Model model) {
		// update model set name = _?, car_kit = ?, engine_type = ?, body_type = ?,
		// brand_id = ?, modified = ? where id = ?(1);

		try (Connection connect = getConnection();
				PreparedStatement pst = connect.prepareStatement("select model_update (?,?,?,?,?,?,?);")) {
			LOGGER.info("execute SQL: Update model");
			pst.setInt(1, model.getId());
			pst.setString(2, model.getName());
			pst.setString(3, model.getCarCit().toString());
			pst.setString(4, model.getEngineType().toString());
			pst.setString(5, model.getBodyType().toString());
			pst.setInt(6, model.getBrandId());
			pst.setTimestamp(7, model.getModified());
			pst.executeUpdate();
		} catch (SQLException e) {
			LOGGER.error("Error from method update {}", e.getMessage());
		}
	}

	@Override
	public Model getById(Integer id) {
		// select * from model where id = ?; select * from model_getById(?)

		try (Connection connect = getConnection();
				PreparedStatement pst = connect.prepareStatement("select * from model where id = ?")) {
			LOGGER.info("execute SQL: show one model");
			pst.setInt(1, id);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				return new Model(rs.getInt(1), rs.getString(2), ECarKit.valueOf(rs.getString(3)),
						EEngineType.valueOf(rs.getString(4)), EBodyType.valueOf(rs.getString(5)), rs.getInt(6),
						rs.getTimestamp(7), rs.getTimestamp(8));
			}
		} catch (SQLException e) {
			LOGGER.error("Error from method getById {}", e.getMessage());
		}
		return null;
	}

	@Override
	public List<Model> getAll() {
		// select * from model;

		List<Model> list = new ArrayList<Model>();

		try (Connection connect = getConnection(); Statement st = connect.createStatement()) {
			LOGGER.info("execute SQL: show all models");
			ResultSet rs = st.executeQuery("select * from model_getAll();");
			while (rs.next()) {
				list.add(new Model(rs.getInt(1), rs.getString(2), ECarKit.valueOf(rs.getString(3)),
						EEngineType.valueOf(rs.getString(4)), EBodyType.valueOf(rs.getString(5)), rs.getInt(6),
						rs.getTimestamp(7), rs.getTimestamp(8)));
			}
		} catch (SQLException e) {
			LOGGER.error("Error from method getAll {}", e.getMessage());
		}
		return list;
	}
}
