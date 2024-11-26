package Model;

import java.time.LocalDate;

public class Bill {

	private int id_Customer;
	private LocalDate date;
	private float total_Payment;
	private int pay_Method;
	private int status;

	public Bill(int id_Customer, LocalDate date, float total_Payment, int pay_Method, int status) {
		super();
		this.id_Customer = id_Customer;
		this.date = date;
		this.total_Payment = total_Payment;
		this.pay_Method = pay_Method;
		this.status = status;
	}

	public int getId_Customer() {
		return id_Customer;
	}

	public void setId_Customer(int id_Customer) {
		this.id_Customer = id_Customer;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public float getTotal_Payment() {
		return total_Payment;
	}

	public void setTotal_Payment(float total_Payment) {
		this.total_Payment = total_Payment;
	}

	public int getPay_Method() {
		return pay_Method;
	}

	public void setPay_Method(int pay_Method) {
		this.pay_Method = pay_Method;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

}
