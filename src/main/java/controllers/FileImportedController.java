package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import model.ImportedFile;
import services.ImportedFileService;
import spring.configuration.ApplicationContextSingleton;

import java.net.URL;
import java.util.ResourceBundle;

public class FileImportedController implements Initializable {

    @FXML
    TableView<ImportedFile> importedFileTable;
    private ObservableList<ImportedFile> data = FXCollections
            .observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        data.addAll(getContext().getAllFiles());
        importedFileTable.getItems().addAll(data);
        importedFileTable.refresh();
    }

    private ImportedFileService getContext() {
        return ApplicationContextSingleton.INSTANCE.getInstance().getBean(ImportedFileService.class);
    }

    public void refreshData() {
        data.clear();
        data.addAll(getContext().getAllFiles());
        importedFileTable.setItems(data);
        importedFileTable.refresh();
    }

}
