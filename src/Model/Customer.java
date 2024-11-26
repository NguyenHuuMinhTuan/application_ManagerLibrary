package Model;

public class Customer {
	private int id;
	private String username;
	private int phone;
	private String email;
	private String address;
	private String memberShip;
	public Customer(int id, String username, int phone, String email, String address, String memberShip) {
		super();
		this.id = id;
		this.username = username;
		this.phone = phone;
		this.email = email;
		this.address = address;
		this.memberShip = memberShip;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public int getPhone() {
		return phone;
	}
	public void setPhone(int phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getMemberShip() {
		return memberShip;
	}
	public void setMemberShip(String memberShip) {
		this.memberShip = memberShip;
	}


}
