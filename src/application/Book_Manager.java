package application;


import java.time.LocalDate;
import java.util.List;

import Model.Book;
import Query_Selector.Query_Book;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class Book_Manager {
	@FXML
	private TextField tf_title, tf_author, tf_description, tf_publisher, tf_publisher_year, tf_quantity, tf_price,
			tf_search, tf_update;

	@FXML
	private ComboBox<String> cb_Category_id;

	@FXML
	private Button btn_add, btn_del, btn_search, btn_edit, btn_show, btn_exit;

	@FXML
	private TableView<Book> TableView;
	@FXML
	private TableColumn<Book, Integer> col_id;
	@FXML
	private TableColumn<Book, String> col_title;
	@FXML
	private TableColumn<Book, String> col_author;
	@FXML
	private TableColumn<Book, Integer> col_category;
	@FXML
	private TableColumn<Book, String> col_pub;
	@FXML
	private TableColumn<Book, LocalDate> col_pub_year;
	@FXML
	private TableColumn<Book, Integer> col_quantity;
	@FXML
	private TableColumn<Book, String> col_des;
	@FXML
	private TableColumn<Book, Float> col_price;

	@FXML
	public void initialize() {

		col_id.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getId()).asObject());
		col_title.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTitle()));
		col_author.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAuthor()));
		col_category.setCellValueFactory(
				cellData -> new SimpleIntegerProperty(cellData.getValue().getCategoryId()).asObject());
		col_pub.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPublisher()));
		col_pub_year
				.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getPublisherYear()));
		col_des.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDescription()));
		col_quantity.setCellValueFactory(
				cellData -> new SimpleIntegerProperty(cellData.getValue().getQuantity()).asObject());
		col_price.setCellValueFactory(cellData -> new SimpleFloatProperty(cellData.getValue().getPrice()).asObject());

		Query_Book.displayBooks(TableView);
		RenderID_Category();
		renderItemsInTextField();
		listenExit();

	}

	public void RenderID_Category() {

		Query_Book processQuery = new Query_Book();

		List<String> categoryIDs = processQuery.getCategoryIDsFromDatabase();

		cb_Category_id.setItems(FXCollections.observableArrayList(categoryIDs));
	}

	@FXML
	public void handleAddBook() {
		String title = tf_title.getText();
		String author = tf_author.getText();
		String categoryIdStr = cb_Category_id.getValue();
		if (categoryIdStr == null) {
			Alert a = new Alert(Alert.AlertType.ERROR, "Giá trị rỗng !");
			a.showAndWait();
		}
		int categoryId = Integer.parseInt(categoryIdStr);
		String publisher = tf_publisher.getText();
		String publisherYear = tf_publisher_year.getText(); // YYYY-MM-DD
		int quantity = Integer.parseInt(tf_quantity.getText());
		String description = tf_description.getText();
		float price = Float.parseFloat(tf_price.getText());

		try {
			Book newBook = new Book(0, title, author, categoryId, publisher, java.time.LocalDate.parse(publisherYear),
					quantity, description, price);

			if (Query_Book.addBook(newBook)) {
				Alert a = new Alert(Alert.AlertType.INFORMATION, "Thêm sách thành công");
				a.showAndWait();
				Query_Book.displayBooks(TableView);
				clearFields();
			} else {
				Alert a = new Alert(Alert.AlertType.ERROR, "Thêm sách thất bại !");
				a.showAndWait();
			}
		} catch (Exception e) {
			e.getStackTrace();
		}
	}

	@FXML
	public void handelUpdateBook() {
		if (tf_update.getText().isEmpty()) {
			Alert a = new Alert(Alert.AlertType.WARNING, "Không được để trống !");
			a.showAndWait();
		
		} else {
			int id_Book = Integer.parseInt(tf_update.getText());
			String title = tf_title.getText();
			String author = tf_author.getText();
			String categoryIdStr = cb_Category_id.getValue();
			if (categoryIdStr == null) {
				Alert a = new Alert(Alert.AlertType.ERROR, "Giá trị rỗng !");
				a.showAndWait();
			}
			int categoryId = Integer.parseInt(categoryIdStr);
			String publisher = tf_publisher.getText();
			String publisherYear = tf_publisher_year.getText(); // YYYY-MM-DD
			int quantity = Integer.parseInt(tf_quantity.getText());
			String description = tf_description.getText();
			float price = Float.parseFloat(tf_price.getText());

			try {
				Book newBook = new Book(0, title, author, categoryId, publisher,
						java.time.LocalDate.parse(publisherYear), quantity, description, price);

				if (Query_Book.updateBook(newBook, id_Book)) {
					Alert a = new Alert(Alert.AlertType.INFORMATION, "Cập nhật sách thành công");
					a.showAndWait();
					Query_Book.displayBooks(TableView);
					clearFields();
				} else {
					Alert a = new Alert(Alert.AlertType.ERROR, "Cập nhật sách thất bại !");
					a.showAndWait();
				}
			} catch (Exception e) {
				e.getStackTrace(); 
			}
		}

	}

	@FXML
	public void handleDeleteBook() {
		Book selectedBook = TableView.getSelectionModel().getSelectedItem();
		if (selectedBook != null) {
			if (Query_Book.deleteBook(selectedBook.getId())) {
				Alert a = new Alert(Alert.AlertType.INFORMATION, "Xoá sách thành công");
				a.showAndWait();
				Query_Book.displayBooks(TableView);
			} else {
				Alert a = new Alert(Alert.AlertType.ERROR, "Xoá sách thất bại !");
				a.showAndWait();
			}
		} else {
			Alert a = new Alert(Alert.AlertType.ERROR, "Có lỗi!");
			a.showAndWait();
		}
	}

	@FXML
	public void handleSearchBook() {
		if (tf_search.getText().isEmpty()) {
			Alert a = new Alert(AlertType.CONFIRMATION, "Không được để trống");
			a.showAndWait();
		} else {
			String keyword = tf_search.getText();

			ObservableList<Book> foundBooks = Query_Book.searchBooks(keyword);

			
			boolean isFound = (foundBooks != null && !foundBooks.isEmpty());

		
			TableView.setItems(foundBooks);

		
			if (isFound) {
				Alert a = new Alert(Alert.AlertType.INFORMATION, "Tìm kiếm thành công");
				a.showAndWait();
			} else {
				Alert a = new Alert(Alert.AlertType.ERROR, "Tìm kiếm thất bại !");
				a.showAndWait();
			}
		}

	}


	public void listenExit() {
		btn_exit.setOnAction(e -> {
	        Stage currentStage = (Stage) btn_exit.getScene().getWindow();
	        currentStage.close();
	    });
	}
	private void clearFields() {
		tf_title.clear();
		tf_author.clear();
		cb_Category_id.getSelectionModel().clearSelection(); // Reset ComboBox
		tf_publisher.clear();
		tf_publisher_year.clear();
		tf_quantity.clear();
		tf_description.clear();
		tf_price.clear();
	}

	public void renderItemsInTextField() {
		TableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue != null) {
				tf_title.setText(newValue.getTitle());
				tf_author.setText(newValue.getAuthor());
				cb_Category_id.setValue(String.valueOf(newValue.getCategoryId()));
				tf_publisher.setText(newValue.getPublisher());
				tf_publisher_year.setText(newValue.getPublisherYear().toString());
				tf_quantity.setText(String.valueOf(newValue.getQuantity()));
				tf_description.setText(newValue.getDescription());
				tf_price.setText(String.valueOf(newValue.getPrice()));

			} else {
				Alert a = new Alert(Alert.AlertType.ERROR, "Giá trị rỗng");
				a.showAndWait();
			}

		});
	}

}
