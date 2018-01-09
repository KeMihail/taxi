package by.itacademy.keikom.taxi.services.sample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ServiceComponent {

	@Autowired
	private DaoComponent dao;

	public DaoComponent getDao() {
		return dao;
	}

	public void setDao(DaoComponent dao) {
		this.dao = dao;
	}

}
