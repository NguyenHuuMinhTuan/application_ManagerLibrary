package Model;

import java.time.LocalDate;

public class BorrowInfo {
	private int id;
	private int idBorrow;
	private int idBook;
	private int quantity;
	private LocalDate dueDate;
	public BorrowInfo(int id, int idBorrow, int idBook, int quantity, LocalDate dueDate) {
		super();
		this.id = id;
		this.idBorrow = idBorrow;
		this.idBook = idBook;
		this.quantity = quantity;
		this.dueDate = dueDate;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getIdBorrow() {
		return idBorrow;
	}
	public void setIdBorrow(int idBorrow) {
		this.idBorrow = idBorrow;
	}
	public int getIdBook() {
		return idBook;
	}
	public void setIdBook(int idBook) {
		this.idBook = idBook;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public LocalDate getDueDate() {
		return dueDate;
	}
	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}
	

}
