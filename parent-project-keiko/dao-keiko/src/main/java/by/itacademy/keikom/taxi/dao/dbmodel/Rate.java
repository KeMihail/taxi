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

	public Rate(Integer id, String name, Double priceLanding, Double priceKilometr, Double priceMinuteWait,
			Timestamp created, Timestamp modified) {
		super();
		this.id = id;
		this.name = name;
		this.priceLanding = priceLanding;
		this.priceKilometr = priceKilometr;
		this.priceMinuteWait = priceMinuteWait;
		this.created = created;
		this.modified = modified;
	}

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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((created == null) ? 0 : created.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((modified == null) ? 0 : modified.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((priceKilometr == null) ? 0 : priceKilometr.hashCode());
		result = prime * result + ((priceLanding == null) ? 0 : priceLanding.hashCode());
		result = prime * result + ((priceMinuteWait == null) ? 0 : priceMinuteWait.hashCode());
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
		Rate other = (Rate) obj;
		if (created == null) {
			if (other.created != null)
				return false;
		} else if (!created.equals(other.created))
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
		if (priceKilometr == null) {
			if (other.priceKilometr != null)
				return false;
		} else if (!priceKilometr.equals(other.priceKilometr))
			return false;
		if (priceLanding == null) {
			if (other.priceLanding != null)
				return false;
		} else if (!priceLanding.equals(other.priceLanding))
			return false;
		if (priceMinuteWait == null) {
			if (other.priceMinuteWait != null)
				return false;
		} else if (!priceMinuteWait.equals(other.priceMinuteWait))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Rate [id=" + id + ", name=" + name + ", priceLanding=" + priceLanding + ", priceKilometr="
				+ priceKilometr + ", priceMinuteWait=" + priceMinuteWait + ", created=" + created + ", modified="
				+ modified + "]";
	}
}
