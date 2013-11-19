// User model here

package ba.etf.tim11.eCinema.models;

import java.util.Date;


public class User
{
	
	int id;
	String lastName;
	String frstName;
	String email;
	String phone;
	String address;
	String dateOfBirth;
	String placeOfBirth;
	State state;
	String password;
	int salt;
	Date updatedAt;
	Date createdAt;
	
	
	public User(int id, String lastName, String frstName, String email,
			String phone, String address, String dateOfBirth,
			String placeOfBirth, State state, String password, int salt,
			Date updatedAt, Date createdAt)
	{
	
		this.id = id;
		this.lastName = lastName;
		this.frstName = frstName;
		this.email = email;
		this.phone = phone;
		this.address = address;
		this.dateOfBirth = dateOfBirth;
		this.placeOfBirth = placeOfBirth;
		this.state = state;
		this.password = password;
		this.salt = salt;
		this.updatedAt = updatedAt;
		this.createdAt = createdAt;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getFrstName() {
		return frstName;
	}
	
	public void setFrstName(String frstName) {
		this.frstName = frstName;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPhone() {
		return phone;
	}
	
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getDateOfBirth() {
		return dateOfBirth;
	}
	
	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	
	public String getPlaceOfBirth() {
		return placeOfBirth;
	}
	
	public void setPlaceOfBirth(String placeOfBirth) {
		this.placeOfBirth = placeOfBirth;
	}
	
	public State getState() {
		return state;
	}
	
	public void setState(State state) {
		this.state = state;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public int getSalt() {
		return salt;
	}
	
	public void setSalt(int salt) {
		this.salt = salt;
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