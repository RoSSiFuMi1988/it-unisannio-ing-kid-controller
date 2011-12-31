package project.univ;

public class Notify {

	private String type;
	private String addr;
	
	public Notify(String type, String addr) {
		super();
		this.type = type;
		this.addr = addr;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
}
