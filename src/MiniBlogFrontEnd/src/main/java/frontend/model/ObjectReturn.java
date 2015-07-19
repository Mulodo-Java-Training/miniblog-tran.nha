package frontend.model;

public class ObjectReturn {
	public String message;
	public User data;
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Object getData() {
		return data;
	}
	public void setData(User data) {
		this.data = data;
	}
}