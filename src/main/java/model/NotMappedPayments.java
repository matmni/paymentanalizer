package model;

import java.util.ArrayList;
import java.util.List;

public class NotMappedPayments {
    private List<Payment> notMappedPayments;

    public NotMappedPayments() {
        this.notMappedPayments = new ArrayList<>();
    }

    public List<Payment> getNotMappedPayments() {
        return notMappedPayments;
    }

    public boolean hasNotMappedPayments() {
        return !notMappedPayments.isEmpty();
    }

}
