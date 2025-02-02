package Model;

public class Account {
	private String username;
	private String password;
	private String email;
	private String phone;
	private int type;

	public Account(String username, String password, String email, String phone, int type) {
		super();
		this.username = username;
		this.password = password;
		this.email = email;
		this.phone = phone;
		this.type = type;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

}
