package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.GridPane;
import javafx.util.converter.DoubleStringConverter;
import model.Company;
import model.CompanyTableData;

import java.io.IOException;
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
    TableColumn<CompanyTableData, String> keyWords;

    @FXML
    Button deleteCompanyButton;

    @FXML
    TableView<CompanyTableData> companiesTable;
    List<Company> companies = new ArrayList<>();

    @FXML
    GridPane viewMenuId;


    private ObservableList<CompanyTableData> data = FXCollections
            .observableArrayList();

    public ViewCompanyListController() {
        companies.add(new Company(1L, "Test1", "Opis", BigDecimal.TEN, "TEST1, Kupa"));
        companies.add(new Company(2L, "Test2", "Opis Firmy 2", BigDecimal.ONE, "TEST2, kupa"));
        companies.add(new Company(3L, "Test trzy", "Opis jakiejś tam firmy", BigDecimal.ZERO, "TEST3, KUPA"));
        companies.add(new Company(4L, "Test 4 cztery testy", "Opis firmy po prostu ostatniej na liście", BigDecimal.valueOf(25L), "TEST4, KUPsztal"));
    }

    private void populateDataIntoTable(List<Company> companies) {
        companies.forEach(c -> data.add(new CompanyTableData(c)));
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        populateDataIntoTable(companies);
        companiesTable.setItems(data);
        companiesTable.setEditable(true);
        name.setCellFactory(
                TextFieldTableCell.forTableColumn());
        description.setCellFactory(
                TextFieldTableCell.forTableColumn());
        keyWords.setCellFactory(
                TextFieldTableCell.forTableColumn());

        amount.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));

        amount.setOnEditCommit(
                (TableColumn.CellEditEvent<CompanyTableData, Double> t) ->
                        (t.getTableView().getItems().get(t.getTablePosition().getRow())).setAmount(t.getNewValue())
        );

        description.setOnEditCommit(
                (TableColumn.CellEditEvent<CompanyTableData, String> t) ->
                        (t.getTableView().getItems().get(t.getTablePosition().getRow())).setDescription(t.getNewValue())
        );
        keyWords.setOnEditCommit(
                (TableColumn.CellEditEvent<CompanyTableData, String> t) ->
                        (t.getTableView().getItems().get(t.getTablePosition().getRow())).setKeyWords(t.getNewValue())
        );
        amount.setOnEditCommit(
                (TableColumn.CellEditEvent<CompanyTableData, Double> t) ->
                        (t.getTableView().getItems().get(t.getTablePosition().getRow())).setAmount(t.getNewValue())
        );
    }

    public void checkSelectedRow() {
        CompanyTableData row = companiesTable.getSelectionModel().getSelectedItem();
        if (row != null) {
            deleteCompanyButton.setDisable(false);
        }
    }

    public void deleteCompanyButton(ActionEvent actionEvent) throws IOException {

        CompanyTableData row = companiesTable.getSelectionModel().getSelectedItem();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Czy chcesz usunąć firmę: " + row.getName() + " ?", ButtonType.YES, ButtonType.NO);
        alert.showAndWait();

        if (alert.getResult() == ButtonType.YES) {
            companiesTable.getItems().remove(row);
            companiesTable.refresh();
        }
    }
}
