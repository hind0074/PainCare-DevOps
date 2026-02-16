package Health;

public class PainTrackParamBean {
	private int id;
	private int paintrack_id;
	private String value;
	private String param;
	
	public int getID() {
		return this.id;
	}
	
	public int getPainTrackID() {
		return this.paintrack_id;
	}
	
	public String getValue() {
		return this.value;
	}
	
	public String getParam() {
		return this.param;
	}
	
	public void setID(int id) {
		this.id = id;
	}
	
	public void setPainTrackID(int paintrack_id) {
		this.paintrack_id = paintrack_id;
	}
	
	public void setValue(String value) {
		this.value = value;
	}
	
	public void setParam(String param) {
		this.param = param;
	}
}
