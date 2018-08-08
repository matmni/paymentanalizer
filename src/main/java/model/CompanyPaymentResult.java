package model;

import java.math.BigDecimal;
import java.util.Date;

public class CompanyPaymentResult {
    private String companyName;
    private String description;
    private String keyWords;
    private BigDecimal rentAmount;
    private Date payDate;
    private Date postDate;
    private boolean paid;
    private BigDecimal rest;

    public CompanyPaymentResult() {
    }

    public CompanyPaymentResult(String companyName, String description, String keyWords, BigDecimal rentAmount, Date payDate, Date postDate, boolean paid, BigDecimal rest) {
        this.companyName = companyName;
        this.description = description;
        this.keyWords = keyWords;
        this.rentAmount = rentAmount;
        this.payDate = payDate;
        this.postDate = postDate;
        this.paid = paid;
        this.rest = rest;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getKeyWords() {
        return keyWords;
    }

    public void setKeyWords(String keyWords) {
        this.keyWords = keyWords;
    }

    public BigDecimal getRentAmount() {
        return rentAmount;
    }

    public void setRentAmount(BigDecimal rentAmount) {
        this.rentAmount = rentAmount;
    }

    public Date getPayDate() {
        return payDate;
    }

    public void setPayDate(Date payDate) {
        this.payDate = payDate;
    }

    public Date getPostDate() {
        return postDate;
    }

    public void setPostDate(Date postDate) {
        this.postDate = postDate;
    }

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    public BigDecimal getRest() {
        return rest;
    }

    public void setRest(BigDecimal rest) {
        this.rest = rest;
    }
}
