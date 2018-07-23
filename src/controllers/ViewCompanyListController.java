package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import model.Company;
import model.CompanyTableData;

import java.math.BigDecimal;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ViewCompanyListController implements Initializable {

    @FXML
    TableColumn<CompanyTableData, Long> id;

    @FXML
    TableColumn<CompanyTableData, String> name;

    @FXML
    TableColumn<CompanyTableData, Double> amount;

    @FXML
    TableColumn<CompanyTableData, String> description;

    @FXML
    TableView<CompanyTableData> companiesTable;
    List<Company> companies = new ArrayList<>();


    private ObservableList<CompanyTableData> data = FXCollections
            .observableArrayList();

    public ViewCompanyListController() {
        companies.add(new Company(1L, "Test1", "Opis", BigDecimal.TEN));
        companies.add(new Company(2L, "Test2", "Opis Firmy 2", BigDecimal.ONE));
        companies.add(new Company(3L, "Test trzy", "Opis jakiejś tam firmy", BigDecimal.ZERO));
        companies.add(new Company(4L, "Test 4 cztery testy", "Opis firmy po prostu ostatniej na liście", BigDecimal.valueOf(25L)));
    }

    private void populateDataIntoTable(List<Company> companies) {
        companies.forEach(c -> data.add(new CompanyTableData(c)));
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        populateDataIntoTable(companies);
        companiesTable.setItems(data);
    }
}
