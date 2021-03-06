package model;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    @Column(name="key_words")
    private String keyWords;
    @Column(name="rent_amount")
    private BigDecimal amount;

    public Company() {
    }

    public Company(Long id, String name, String description, BigDecimal amount, String keyWords) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.amount = amount;
        this.keyWords = keyWords;
    }
    public Company(String name, String description, BigDecimal amount, String keyWords) {
        this.name = name;
        this.description = description;
        this.amount = amount;
        this.keyWords = keyWords;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Company{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", amount=" + amount +
                ", keyWords=" + keyWords +
                '}';
    }

    public String getKeyWords() {
        return keyWords;
    }

    public void setKeyWords(String keyWords) {
        this.keyWords = keyWords;
    }
}
