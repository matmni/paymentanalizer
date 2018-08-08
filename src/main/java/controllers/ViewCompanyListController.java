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
import services.CompanyService;
import spring.configuration.ApplicationContextSingleton;

import java.io.IOException;
import java.net.URL;
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

    @FXML
    GridPane viewMenuId;

    private ObservableList<CompanyTableData> data = FXCollections
            .observableArrayList();

    private void populateDataIntoTable(List<Company> companies) {
        companies.forEach(c -> data.add(new CompanyTableData(c)));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        refreshData();
        companiesTable.setEditable(true);
        name.setCellFactory(
                TextFieldTableCell.forTableColumn());
        description.setCellFactory(
                TextFieldTableCell.forTableColumn());
        keyWords.setCellFactory(
                TextFieldTableCell.forTableColumn());

        amount.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));

        amount.setOnEditCommit(
                (TableColumn.CellEditEvent<CompanyTableData, Double> t) -> {
                    (t.getTableView().getItems().get(t.getTablePosition().getRow())).setAmount(t.getNewValue());
                    getContext().updateCompany(t.getTableView().getItems().get(t.getTablePosition().getRow()));
                }
        );

        name.setOnEditCommit(
                (TableColumn.CellEditEvent<CompanyTableData, String> t) -> {
                    (t.getTableView().getItems().get(t.getTablePosition().getRow())).setName(t.getNewValue());
                    getContext().updateCompany(t.getTableView().getItems().get(t.getTablePosition().getRow()));
                }
        );

        description.setOnEditCommit(
                (TableColumn.CellEditEvent<CompanyTableData, String> t) -> {
                    (t.getTableView().getItems().get(t.getTablePosition().getRow())).setDescription(t.getNewValue());
                    getContext().updateCompany(t.getTableView().getItems().get(t.getTablePosition().getRow()));
                }
        );
        keyWords.setOnEditCommit(
                (TableColumn.CellEditEvent<CompanyTableData, String> t) -> {
                    (t.getTableView().getItems().get(t.getTablePosition().getRow())).setKeyWords(t.getNewValue());
                    getContext().updateCompany(t.getTableView().getItems().get(t.getTablePosition().getRow()));
                }
        );
        amount.setOnEditCommit(
                (TableColumn.CellEditEvent<CompanyTableData, Double> t) -> {
                    (t.getTableView().getItems().get(t.getTablePosition().getRow())).setAmount(t.getNewValue());
                    getContext().updateCompany(t.getTableView().getItems().get(t.getTablePosition().getRow()));
                }
        );
    }

    public void refreshData() {
        data.clear();
        populateDataIntoTable(getContext().getAllCompanies());
        companiesTable.setItems(data);
        companiesTable.refresh();
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
            getContext().deteCompany(row);
            refreshData();
            deleteCompanyButton.setDisable(true);
        }
    }

    public void refreshListButton(ActionEvent actionEvent) {
        refreshData();
    }

    private CompanyService getContext() {
        return ApplicationContextSingleton.INSTANCE.getInstance().getBean(CompanyService.class);
    }

}
