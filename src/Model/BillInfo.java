package Model;

public class BillInfo {
	
	private int id;
	private int id_Bill;
	private int id_Book;
	private int quantity;
	private float price;
	private float discount;
	public BillInfo(int id, int id_Bill, int id_Book, int quantity, float price, float discount) {
		super();
		this.id = id;
		this.id_Bill = id_Bill;
		this.id_Book = id_Book;
		this.quantity = quantity;
		this.price = price;
		this.discount = discount;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getId_Bill() {
		return id_Bill;
	}
	public void setId_Bill(int id_Bill) {
		this.id_Bill = id_Bill;
	}
	public int getId_Book() {
		return id_Book;
	}
	public void setId_Book(int id_Book) {
		this.id_Book = id_Book;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public float getDiscount() {
		return discount;
	}
	public void setDiscount(float discount) {
		this.discount = discount;
	}
	

}
