package ba.etf.tim11.eCinema.models;

import java.math.BigDecimal;
import java.util.Date;

public class Receipt 
{
	int id;
	BigDecimal totalPrice;
	BigDecimal discount;
	Date updatedAt;
	Date cretedAt;
	
	public Receipt(int id, BigDecimal totalPrice, BigDecimal discount,
			Date updatedAt, Date cretedAt)
	{
		this.id = id;
		this.totalPrice = totalPrice;
		this.discount = discount;
		this.updatedAt = updatedAt;
		this.cretedAt = cretedAt;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public BigDecimal getTotalPrice() {
		return totalPrice;
	}
	
	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}
	
	public BigDecimal getDiscount() {
		return discount;
	}
	
	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}
	
	public Date getUpdatedAt() {
		return updatedAt;
	}
	
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	
	public Date getCretedAt() {
		return cretedAt;
	}
	
	public void setCretedAt(Date cretedAt) {
		this.cretedAt = cretedAt;
	}
}
