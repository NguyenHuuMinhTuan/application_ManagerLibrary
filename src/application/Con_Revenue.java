package application;

import Model.Revenue;
import Query_Selector.Query_Revenue;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;

import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class Con_Revenue {
    @FXML
    private TableView<Revenue> tb_Revenue;

    @FXML
    private LineChart<String, Number> ch_Revenue;
    
    @FXML
    private TableColumn<Revenue, Integer> col_Year;

    @FXML
    private TableColumn<Revenue, Integer> col_Month;

    @FXML
    private TableColumn<Revenue, Float> col_Revenue;
    
    private ObservableList<Revenue> revenueList;

    public void initialize() {
        Query_Revenue query = new Query_Revenue();
        revenueList = query.getRevenue();
        renderRevenue();
        renderChart();
    }
    public void renderRevenue() {
        
        col_Year.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getYear()).asObject());
        col_Month.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getMonth()).asObject());
        col_Revenue.setCellValueFactory(cellData -> new SimpleFloatProperty(cellData.getValue().getRevenue()).asObject());

        Query_Revenue query = new Query_Revenue();
        ObservableList<Revenue> revenueList = query.getRevenue();
        tb_Revenue.setItems(revenueList);
    }
    public void renderChart() {
        Query_Revenue query = new Query_Revenue();
        ObservableList<Revenue> revenueList = query.getRevenue();

        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Monthly Revenue");

        for (Revenue revenue : revenueList) {
            // Tạo trục X với định dạng "Year-Month"
            String monthYear = revenue.getYear() + "-" + revenue.getMonth();
            series.getData().add(new XYChart.Data<>(monthYear, revenue.getRevenue()));
        }

        // Thêm series vào LineChart
        ch_Revenue.getData().clear(); // Xóa dữ liệu cũ (nếu có)
        ch_Revenue.getData().add(series);
    }
}
