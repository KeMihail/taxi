package by.itacademy.keikom.taxi.dao.dbmodel;

import java.sql.Timestamp;

public class LegalEntity {

	public LegalEntity() {
	}

	private Integer id;
	private String name;
	private String address;
	private String phoneNumber;
	private String email;
	private Timestamp created;
	private Timestamp modified;

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
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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
	public String toString() {
		return "LegalEntity [id=" + id + ", name=" + name + ", address=" + address + ", phone_number=" + phoneNumber
				+ ", email=" + email + ", created=" + created + ", modified=" + modified + "]";
	}
}
