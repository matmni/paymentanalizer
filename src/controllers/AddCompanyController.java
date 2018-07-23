package controllers;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import model.Company;
import util.ExceptionBuilder;

import java.math.BigDecimal;


public class AddCompanyController {

    private static final String COMPANY_NAME_ID = "companyNameId";
    private static final String COMPANY_DESCRIPTION_ID = "companyDescriptionId";
    private static final String COMPANY_AMOUNT_ID = "companyAmountId";

    @FXML
    GridPane companyDataGridId;

    @FXML
    private Text actiontarget;

    @FXML
    private Button addCompanyButton;

    @FXML
    private Button clearDataButton;

    @FXML
    public void initialize() {
        ColumnConstraints columnOneConstrains = new ColumnConstraints(160, 160, Double.MAX_VALUE);
        ColumnConstraints columnTwoConstrains = new ColumnConstraints(250, 250, Double.MAX_VALUE);
        companyDataGridId.getColumnConstraints().addAll(columnOneConstrains, columnTwoConstrains);
    }

    @FXML
    public void clearDataButton(ActionEvent e) {
        ObservableList<Node> childrens = companyDataGridId.getChildren();
        ((TextField) getNode(COMPANY_NAME_ID, childrens)).setText("");
        ((TextField) getNode(COMPANY_DESCRIPTION_ID, childrens)).setText("");
        ((TextField) getNode(COMPANY_AMOUNT_ID, childrens)).setText("");
        clearDataButton.setDisable(true);
        actiontarget.setText("");
    }

    @FXML
    public void addCompanyToDb(ActionEvent e) {
        ObservableList<Node> childrens = companyDataGridId.getChildren();
        String name = getTextFromField(COMPANY_NAME_ID, childrens);
        String amount = getTextFromField(COMPANY_AMOUNT_ID, childrens);
        String description = getTextFromField(COMPANY_DESCRIPTION_ID, childrens);
        String message = "Pomyślnie dodano firmę";
        try {
            /**
             * TODO.
             * Dodanie do bazy.
             */
            Company company = new Company(0L, name, description, new BigDecimal(amount));
            System.out.println(company.toString());
            actiontarget.setFill(Color.GREEN);
        } catch (Exception ex) {
            actiontarget.setFill(Color.FIREBRICK);
            message = ExceptionBuilder.getExceptionMessage(ex);
        }
        actiontarget.setText(message);
        clearDataButton.setDisable(false);
    }

    private Node getNode(String fieldId, ObservableList<Node> childrens) {
        for (Node node : childrens) {
            if (fieldId.equals(node.getId())) {
                return node;
            }
        }
        return null;
    }

    private String getTextFromField(String fieldId, ObservableList<Node> childrens) {
        return ((TextField) getNode(fieldId, childrens)).getText();
    }
}
