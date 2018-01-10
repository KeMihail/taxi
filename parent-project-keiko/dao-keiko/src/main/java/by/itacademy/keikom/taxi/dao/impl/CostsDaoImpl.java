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

import by.itacademy.keikom.taxi.dao.ICostsDao;
import by.itacademy.keikom.taxi.dao.dbmodel.Costs;
import by.itacademy.keikom.taxi.dao.exeption.SQLExecutionException;

@Repository
public class CostsDaoImpl extends AbstractDaoImpl implements ICostsDao {

	private static final Logger LOGGER = LoggerFactory.getLogger(CostsDaoImpl.class);

	@Override
	public Integer create(Costs costs) {

		try (Connection connect = getConnection();
				PreparedStatement pst = connect.prepareStatement(
						"insert into costs (car_id,taxes,technical_inspection,insurance,"
								+ "car_service,pretrip_inspection,salary_driver,\r\n"
								+ "fuel_consumption,other,created,modified)\r\n" + "values (?,?,?,?,?,?,?,?,?,?,?);",
						Statement.RETURN_GENERATED_KEYS)) {
			LOGGER.info("execute SQL: create new Costs to Car");

			pst.setInt(1, costs.getCarId());
			pst.setDouble(2, costs.getTaxes());
			pst.setDouble(3, costs.getTechnicalInspection());
			pst.setDouble(4, costs.getInsurance());
			pst.setDouble(5, costs.getCarService());
			pst.setDouble(6, costs.getPretripInspection());
			pst.setDouble(7, costs.getSalaryDriver());
			pst.setDouble(8, costs.getFuelConsumption());
			pst.setDouble(9, costs.getOther());
			pst.setTimestamp(10, costs.getCreated());
			pst.setTimestamp(11, costs.getModified());
			pst.executeUpdate();

			ResultSet rs = pst.getGeneratedKeys();
			rs.next();
			Integer id = rs.getInt("car_id");
			return id;
		} catch (SQLException e) {
			throw new SQLExecutionException(e);
		}
	}

	@Override
	public void delete(Integer id) {

		try (Connection connect = getConnection();
				PreparedStatement pst = connect.prepareStatement("delete from costs where car_id = ?")) {
			LOGGER.info("execute SQL: delete Costs to Car");

			pst.setInt(1, id);
			pst.executeUpdate();
		} catch (SQLException e) {
			LOGGER.error("Error from method delete {}", e.getMessage());
		}
	}

	@Override
	public void update(Costs costs) {

		try (Connection connect = getConnection();
				PreparedStatement pst = connect.prepareStatement("update costs\r\n"
						+ "set taxes = ?, technical_inspection = ?, insurance = ?,  car_service = ?,\r\n"
						+ "pretrip_inspection = ?, salary_driver = ?, fuel_consumption = ?, other = ?, modified = ?\r\n"
						+ "where car_id = ?")) {
			LOGGER.info("execute SQL: update costs to Car");

			pst.setDouble(1, costs.getTaxes());
			pst.setDouble(2, costs.getTechnicalInspection());
			pst.setDouble(3, costs.getInsurance());
			pst.setDouble(4, costs.getCarService());
			pst.setDouble(5, costs.getPretripInspection());
			pst.setDouble(6, costs.getSalaryDriver());
			pst.setDouble(7, costs.getFuelConsumption());
			pst.setDouble(8, costs.getOther());
			pst.setTimestamp(9, costs.getModified());
			pst.setInt(10, costs.getCarId());
			pst.executeUpdate();

		} catch (SQLException e) {
			LOGGER.error("Error from method update {}", e.getMessage());
		}
	}

	@Override
	public Costs getById(Integer id) {

		try (Connection connect = getConnection();
				PreparedStatement pst = connect.prepareStatement("select * from costs where car_id = ?")) {
			LOGGER.info("execute SQL: show costs one Car");

			pst.setInt(1, id);

			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				return parseCosts(rs);
			}
		} catch (SQLException e) {
			LOGGER.error("Error from method getById {}", e.getMessage());
		}
		return null;
	}

	@Override
	public List<Costs> getAll() {

		List<Costs> list = new ArrayList<Costs>();
		try (Connection connect = getConnection(); Statement st = connect.createStatement()) {
			LOGGER.info("execute SQL: show costs all Cars");

			ResultSet rs = st.executeQuery("select * from costs");
			while (rs.next()) {
				list.add(parseCosts(rs));
			}
		} catch (SQLException e) {
			LOGGER.error("Error from method getAll {}", e.getMessage());
		}
		return list;
	}

	private Costs parseCosts(ResultSet rs) throws SQLException {
		Costs costs = new Costs();
		costs.setCarId(rs.getInt(1));
		costs.setTaxes(rs.getDouble(2));
		costs.setTechnicalInspection(rs.getDouble(3));
		costs.setInsurance(rs.getDouble(4));
		costs.setCarService(rs.getDouble(5));
		costs.setPretripInspection(rs.getDouble(6));
		costs.setSalaryDriver(rs.getDouble(7));
		costs.setFuelConsumption(rs.getDouble(8));
		costs.setOther(rs.getDouble(9));
		costs.setCreated(rs.getTimestamp(10));
		costs.setModified(rs.getTimestamp(11));
		return costs;
	}

}
