package Blog;

import java.sql.Timestamp;

public class CommentBean {
	private int id;
	private int user_id;
	private int blog_id;
	private String content;
	private Timestamp date;
	private String user_name;
	private String avatar;
	
	public int getID() {
		return this.id;
	}
	
	public int getUserID() {
		return this.user_id;
	}
	
	public int getBlogID() {
		return this.blog_id;
	}
	
	public String getContent() {
		return this.content;
	}
	
	public Timestamp getDate() {
		return this.date;
	}
	
	public String getUserName() {
		return this.user_name;
	}
	
	public String getAvatar() {
		return this.avatar;
	}
	
	public void setID(int id) {
		this.id = id;
	}
	
	public void setUserID(int user_id) {
		this.user_id = user_id;
	}
	
	public void setBlogID(int blog_id) {
		this.blog_id = blog_id;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	
	public void setDate(Timestamp date) {
		this.date = date;
	}
	
	public void setUserName(String name) {
		this.user_name = name;
	}
	
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
}
