package by.itacademy.keikom.taxi.dao.dbmodel;

import java.sql.Timestamp;

import by.itacademy.keikom.taxi.dao.exeption.NotValidPhoneNumberException;

public class LegalEntity extends AbstractDBModel {

	public LegalEntity() {
	}

	private Integer id;
	private String name;
	private String address;
	private String phoneNumber;
	private String email;
	private Timestamp created;
	private Timestamp modified;

	public LegalEntity(Integer id, String name, String address, String phoneNumber, String email, Timestamp created,
			Timestamp modified) {
		this.id = id;
		this.name = name;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.created = created;
		this.modified = modified;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone_number() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		if (validatePhoneNumber(phoneNumber)) {
			this.phoneNumber = phoneNumber;
		} else
			throw new NotValidPhoneNumberException();

	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		if (validateEmailAddress(email)) {
			this.email = email;
		}
	}

	public Timestamp getCreated() {
		return created;
	}

	public void setCreated(Timestamp created) {
		this.created = created;
	}

	public Timestamp getModified() {
		return modified;
	}

	public void setModified(Timestamp modified) {
		this.modified = modified;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((created == null) ? 0 : created.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((modified == null) ? 0 : modified.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((phoneNumber == null) ? 0 : phoneNumber.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LegalEntity other = (LegalEntity) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (created == null) {
			if (other.created != null)
				return false;
		} else if (!created.equals(other.created))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (modified == null) {
			if (other.modified != null)
				return false;
		} else if (!modified.equals(other.modified))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (phoneNumber == null) {
			if (other.phoneNumber != null)
				return false;
		} else if (!phoneNumber.equals(other.phoneNumber))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "LegalEntity [id=" + id + ", name=" + name + ", address=" + address + ", phone_number=" + phoneNumber
				+ ", email=" + email + ", created=" + created + ", modified=" + modified + "]";
	}
}
