package Model;

import java.time.LocalDate;

public class Book {
		private int id;
	    private String title;
	    private String author;
	    private int categoryId;
	    private String publisher;
	    private LocalDate publisherYear;
	    private int quantity;
	    private String description;
	    private float price;

	    // Constructor
	    public Book(int id, String title, String author, int categoryId, String publisher, LocalDate publisherYear, int quantity, String description, float price) {
	        this.id = id;
	        this.title = title;
	        this.author = author;
	        this.categoryId = categoryId;
	        this.publisher = publisher;
	        this.publisherYear = publisherYear;
	        this.quantity = quantity;
	        this.description = description;
	        this.price = price;
	    }

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public String getAuthor() {
			return author;
		}

		public void setAuthor(String author) {
			this.author = author;
		}

		public int getCategoryId() {
			return categoryId;
		}

		public void setCategoryId(int categoryId) {
			this.categoryId = categoryId;
		}

		public String getPublisher() {
			return publisher;
		}

		public void setPublisher(String publisher) {
			this.publisher = publisher;
		}

		public LocalDate getPublisherYear() {
			return publisherYear;
		}

		public void setPublisherYear(LocalDate publisherYear) {
			this.publisherYear = publisherYear;
		}

		public int getQuantity() {
			return quantity;
		}

		public void setQuantity(int quantity) {
			this.quantity = quantity;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		public float getPrice() {
			return price;
		}

		public void setPrice(float price) {
			this.price = price;
		}

}
