package Health;

public class PainTrackBean {
	private int id;
	private int user_id;
	private int level;
	
	public void setID(int id) {
		this.id = id;
	}
	
	public void setUserID(int id) {
		this.user_id = id;
	}
	
	public void setLevel(int level) {
		this.level = level;
	}
	
	
	public int geID() {
		return this.id;
	}
	
	public int getUserID() {
		return this.user_id;
	}
	
	public int getLevel() {
		return this.level;
	}
	
}
