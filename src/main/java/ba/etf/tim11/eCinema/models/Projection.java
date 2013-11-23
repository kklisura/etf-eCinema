package ba.etf.tim11.eCinema.models;

import java.math.BigDecimal;
import java.util.Date;

public class Projection
{
	int id;
	Date time;
	BigDecimal pricePerSeat;
	Content content;
	CinemaHall cinemaHall;
	ProjectionType projectionType;
	Date updatedAt;
	Date createdAt;
	
	public Projection(int id, Date time, BigDecimal pricePerSeat, Content content,
			CinemaHall cinemaHall, ProjectionType projectionType,
			Date updatedAt, Date createdAt)
	{
		this.id = id;
		this.time = time;
		this.pricePerSeat = pricePerSeat;
		this.content = content;
		this.cinemaHall = cinemaHall;
		this.projectionType = projectionType;
		this.updatedAt = updatedAt;
		this.createdAt = createdAt;
	}
	
	public Projection() {
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public Date getTime() {
		return time;
	}
	
	public void setTime(Date time) {
		this.time = time;
	}
	
	public BigDecimal getPricePerSeat() {
		return pricePerSeat;
	}
	
	public void setPricePerSeat(BigDecimal pricePerSeat) {
		this.pricePerSeat = pricePerSeat;
	}
	
	public Content getContent() {
		return content;
	}
	
	public void setContent(Content content) {
		this.content = content;
	}
	
	public CinemaHall getCinemaHall() {
		return cinemaHall;
	}
	
	public void setCinemaHall(CinemaHall cinemaHall) {
		this.cinemaHall = cinemaHall;
	}
	
	public ProjectionType getProjectionType() {
		return projectionType;
	}
	
	public void setProjectionType(ProjectionType projectionType) {
		this.projectionType = projectionType;
	}
	
	public Date getUpdatedAt() {
		return updatedAt;
	}
	
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	
}
