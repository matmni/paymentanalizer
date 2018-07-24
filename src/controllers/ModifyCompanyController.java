package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import model.CompanyTableData;

import java.net.URL;
import java.util.ResourceBundle;

public class ModifyCompanyController implements Initializable {
    @FXML
    TextField companyNameId;

    @FXML
    TextField companyDescriptionId;

    @FXML
    TextField companyAmountId;

    private static CompanyTableData row;

    @FXML
    public void initialize() {
    }

    public void modifyCompany(ActionEvent actionEvent) {

    }

    public void cancelModify(ActionEvent actionEvent) {

    }

    public static CompanyTableData getRow() {
        return row;
    }

    public void setRow(CompanyTableData row) {
        ModifyCompanyController.row = row;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        if (row != null) {
            companyNameId.setText(row.getName());
            companyDescriptionId.setText(row.getDescription());
            companyAmountId.setText(row.getAmount().toString());
        }
    }
}
