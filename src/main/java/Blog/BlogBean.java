package Blog;

import java.sql.Date;
import java.sql.Timestamp;

public class BlogBean {
	private int id;
	private int user_id;
	private String title;
	private String description;
	private String image;
	private Date date;
	private String user_name;
	private String user_image;
	
	
	public int getID() {
		return this.id;
	}
	
	public int getUserID() {
		return this.user_id;
	}
	
	public String getTitle() {
		return this.title;
	}
	
	public String getDescription() {
		return this.description;
	}
	
	public String getImage() {
		return this.image;
	}
	public String getUserImage() {
		return this.user_image;
	}
	
	public Date getDate() {
		return this.date;
	}
	
	public String getUserName() {
		return this.user_name;
	}
	
	public void setID(int id) {
		this.id = id;
	}
	
	public void setUserID(int user_id) {
		this.user_id = user_id;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public void setImage(String image) {
		this.image = image;
	}
	public String setUserImage(String user_image) {
		return this.user_image=user_image;
	}
	

	public void setDate(Date date) {
		this.date = date;
	}
	
	public void setUserName(String name) {
		this.user_name = name;
	}
}
