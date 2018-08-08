package model;

import java.util.List;

public class IntervalResults {
    List<CompanyPaymentResult>  companyPaymentResults;
    List<Payment> notMappedPayments;

    public List<CompanyPaymentResult> getCompanyPaymentResults() {
        return companyPaymentResults;
    }

    public void setCompanyPaymentResults(List<CompanyPaymentResult> companyPaymentResults) {
        this.companyPaymentResults = companyPaymentResults;
    }

    public List<Payment> getNotMappedPayments() {
        return notMappedPayments;
    }

    public void setNotMappedPayments(List<Payment> notMappedPayments) {
        this.notMappedPayments = notMappedPayments;
    }
}
