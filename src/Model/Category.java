package Model;

public class Category {
	private int id;
	private String nameCategory;
	public Category(int id, String nameCategory) {
		super();
		this.id = id;
		this.nameCategory = nameCategory;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNameCategory() {
		return nameCategory;
	}
	public void setNameCategory(String nameCategory) {
		this.nameCategory = nameCategory;
	}
	

}
