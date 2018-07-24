package controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
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
    Button modifyCompanyButton;

    @FXML
    Button deleteCompanyButton;

    @FXML
    TableView<CompanyTableData> companiesTable;
    List<Company> companies = new ArrayList<>();

    @FXML
    VBox modifyCompanyView;

    @FXML
    GridPane viewMenuId;


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
        companiesTable.setEditable(true);
        name.setCellFactory(
                TextFieldTableCell.forTableColumn());

        name.setOnEditCommit(
                (TableColumn.CellEditEvent<CompanyTableData, String> t) ->
                        (t.getTableView().getItems().get(t.getTablePosition().getRow())).setName(t.getNewValue())
        );

    }

    public void checkSelectedRow() {
        CompanyTableData row = companiesTable.getSelectionModel().getSelectedItem();
        if (row != null) {
            modifyCompanyButton.setDisable(false);
            deleteCompanyButton.setDisable(false);
        }
    }

    public void modifyCompany(ActionEvent actionEvent) {
        CompanyTableData row = companiesTable.getSelectionModel().getSelectedItem();
        if (row != null) {

            ModifyCompanyController modifyCompanyController = new ModifyCompanyController();
            modifyCompanyController.setRow(row);
            modifyCompanyController.initialize(null, null);
            viewMenuId.setVisible(false);
            modifyCompanyView.setVisible(true);
            modifyCompanyView.setMinHeight(100.0);
        }
    }

    public void deleteCompanyButton(ActionEvent actionEvent) throws IOException {

        CompanyTableData row = companiesTable.getSelectionModel().getSelectedItem();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Czy chcesz usunąć firmę: " + row.getName() + " ?", ButtonType.YES, ButtonType.NO);
        alert.showAndWait();

        if (alert.getResult() == ButtonType.YES) {
            companiesTable.getItems().removeAll(row);
        }
    }
}
