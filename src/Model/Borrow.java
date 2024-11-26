package Model;

import java.time.LocalDate;

public class Borrow {
	private int id ;
	private int idCustomer;
	private LocalDate borrowDate;
	private LocalDate returnDate;
	private int status;
	public Borrow(int id, int idCustomer, LocalDate borrowDate, LocalDate returnDate, int status) {
		super();
		this.id = id;
		this.idCustomer = idCustomer;
		this.borrowDate = borrowDate;
		this.returnDate = returnDate;
		this.status = status;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getIdCustomer() {
		return idCustomer;
	}
	public void setIdCustomer(int idCustomer) {
		this.idCustomer = idCustomer;
	}
	public LocalDate getBorrowDate() {
		return borrowDate;
	}
	public void setBorrowDate(LocalDate borrowDate) {
		this.borrowDate = borrowDate;
	}
	public LocalDate getReturnDate() {
		return returnDate;
	}
	public void setReturnDate(LocalDate returnDate) {
		this.returnDate = returnDate;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
	

}
