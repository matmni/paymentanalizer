package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import model.CompanyPaymentResult;
import model.IntervalResults;
import model.Payment;
import services.PaymentService;
import spring.configuration.ApplicationContextSingleton;

import java.math.BigDecimal;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class PaymentsController implements Initializable {
    @FXML
    DatePicker fromDatePicker;
    @FXML
    DatePicker toDatePicker;
    @FXML
    Button refreshListButton;

    @FXML
    TableColumn companyName;

    @FXML
    TableView<CompanyPaymentResult> paymentsTable;
    @FXML
    TableView<Payment> notMappedPaymentsTable;
    private ObservableList<CompanyPaymentResult> paymentData = FXCollections
            .observableArrayList();
    private ObservableList<Payment> notMappedPayments = FXCollections
            .observableArrayList();

    public void searchPayments(ActionEvent actionEvent) throws ParseException {
        paymentData.clear();
        paymentsTable.getItems().clear();
        notMappedPayments.clear();
        notMappedPaymentsTable.getItems().clear();

        IntervalResults result = getMappedDataFromDb();


        notMappedPayments.addAll(result.getNotMappedPayments());
        notMappedPaymentsTable.getItems().addAll(notMappedPayments);
        notMappedPaymentsTable.refresh();

        paymentData.addAll(result.getCompanyPaymentResults());
        paymentsTable.getItems().addAll(paymentData);
        paymentsTable.refresh();

    }

    private IntervalResults getMappedDataFromDb() throws ParseException {
        LocalDate fromDate = fromDatePicker.getValue();
        LocalDate toDate = toDatePicker.getValue();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return getContext().calculatePayments(sdf.parse(fromDate.toString()), sdf.parse(toDate.toString()));
    }

    public void validateRange(ActionEvent actionEvent) {
        LocalDate fromDate = fromDatePicker.getValue();
        LocalDate toDate = toDatePicker.getValue();

        if (fromDate != null && toDate != null && fromDate.compareTo(toDate) < 0) {
            refreshListButton.setDisable(false);
        } else {
            refreshListButton.setDisable(true);
        }
    }

    private PaymentService getContext() {
        return ApplicationContextSingleton.INSTANCE.getInstance().getBean(PaymentService.class);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        paymentsTable.setPlaceholder(new Label("Tabela dla wyliczonych należności"));
        notMappedPaymentsTable.setPlaceholder(new Label("Tabela dla niezmapowanych wpłat"));
        companyName.setCellFactory(column -> {
            return new TableCell<CompanyPaymentResult, String>() {
                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    setText(empty ? "" : getItem().toString());
                    TableRow<CompanyPaymentResult> currentRow = getTableRow();
                    if (currentRow.getItem() != null) {
                        if (currentRow.getItem().getRest().compareTo(BigDecimal.ZERO) == 0) {
                            currentRow.setStyle("-fx-background-color:lightgreen");
                        } else {
                            currentRow.setStyle("-fx-background-color:pink");
                        }
                    }
                }
            };
        });
    }
}
