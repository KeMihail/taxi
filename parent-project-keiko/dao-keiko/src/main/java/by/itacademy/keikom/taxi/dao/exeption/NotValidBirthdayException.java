package by.itacademy.keikom.taxi.dao.exeption;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NotValidBirthdayException extends RuntimeException {

	private static final Logger LOGGER = LoggerFactory.getLogger(NotValidPhoneNumberException.class);

	public NotValidBirthdayException() {
		LOGGER.error("Error input Birthday");
	}
}
