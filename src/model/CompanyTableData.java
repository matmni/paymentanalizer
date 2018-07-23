package model;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;

public class CompanyTableData {
    private SimpleStringProperty name;
    private SimpleLongProperty id;
    private SimpleDoubleProperty amount;
    private SimpleStringProperty description;

    public CompanyTableData(Company c) {
        name = new SimpleStringProperty(c.getName());
        id = new SimpleLongProperty(c.getId());
        amount = new SimpleDoubleProperty(c.getAmount().doubleValue());
        description = new SimpleStringProperty(c.getDescription());
    }

    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public Long getId() {
        return id.get();
    }

    public SimpleLongProperty idProperty() {
        return id;
    }


    public void setId(long id) {
        this.id.set(id);
    }

    public double getAmount() {
        return amount.get();
    }

    public SimpleDoubleProperty amountProperty() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount.set(amount);
    }

    public String getDescription() {
        return description.get();
    }

    public SimpleStringProperty descriptionProperty() {
        return description;
    }

    public void setDescription(String description) {
        this.description.set(description);
    }
}
