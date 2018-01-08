package by.itacademy.keikom.taxi.dao.dbmodel;

import java.sql.Timestamp;

public class Rate {

	private Integer id;
	private String name;
	private Double priceLanding;
	private Double priceKilometr;
	private Double priceMinuteWait;
	private Timestamp created;
	private Timestamp modified;

	public Rate() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getPriceLanding() {
		return priceLanding;
	}

	public void setPriceLanding(Double priceLanding) {
		this.priceLanding = priceLanding;
	}

	public Double getPriceKilometr() {
		return priceKilometr;
	}

	public void setPriceKilometr(Double priceKilometr) {
		this.priceKilometr = priceKilometr;
	}

	public Double getPriceMinuteWait() {
		return priceMinuteWait;
	}

	public void setPriceMinuteWait(Double priceMinuteWait) {
		this.priceMinuteWait = priceMinuteWait;
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
		return "Rate [id=" + id + ", name=" + name + ", priceLanding=" + priceLanding + ", priceKilometr="
				+ priceKilometr + ", priceMinuteWait=" + priceMinuteWait + ", created=" + created + ", modified="
				+ modified + "]";
	}
}
