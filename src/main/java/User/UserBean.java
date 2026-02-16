package User;

import java.sql.Date;

public class UserBean {
	private int id;
	private String name;
	private String email;
	private String avatar;
	private Date birthDay;
	
	public int getID() {
		return this.id;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getEmail() {
		return this.email;
	}
	
	public String getAvatar() {
		return this.avatar;
	}
	
	public Date getBirthDay() {
		return this.birthDay;
	}
	
	public void setID(int id) {
		this.id = id;
	}
	
	public void setName(String value) {
		this.name = value;
	}
	
	public void setEmail(String value) {
		this.email = value;
	}
	
	public void setAvatar(String value) {
		this.avatar = value;
	}
	
	public void setBirthDay(Date value) {
		this.birthDay = value;
	}
}
