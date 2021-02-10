package com.example.MLS.entity;

import com.example.MLS.entity.House;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "mortgage")
public class Mortgage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "house_id")
    private House house;

    @Column(name = "total_initial_loan", nullable = false, scale = 2)
    private Float totalInitialLoan;

    @Column(name = "first_payment_date", nullable = false, length = 64)
    private Date firstPaymentDate;

    @Column(name = "interest_rate", nullable = false, scale = 4)
    private Float interestRate;

    @Column(name = "monthly_payment", nullable = false, scale = 2)
    private Float monthlyPayment;

    @Column(name = "length", nullable = false, length = 3)
    private Integer length;

    @Column(name = "PI", scale = 2)
    private Float PI;

    @Column(name = "property_tax_monthly", scale = 2)
    private Float propertyTaxMonthly;

    @Column(name = "monthly_due_day")
    private Integer monthlyDueDay;

    @Column(name = "bank_name", length = 64)
    private String bankName;

    public Mortgage() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public House getHouse() {
        return house;
    }

    public void setHouse(House house) {
        this.house = house;
    }

    public Float getTotalInitialLoan() {
        return totalInitialLoan;
    }

    public void setTotalInitialLoan(Float totalInitialLoan) {
        this.totalInitialLoan = totalInitialLoan;
    }

    public Date getFirstPaymentDate() {
        return firstPaymentDate;
    }

    public void setFirstPaymentDate(Date firstPaymentDate) {
        this.firstPaymentDate = firstPaymentDate;
    }

    public Float getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(Float interestRate) {
        this.interestRate = interestRate;
    }

    public Float getMonthlyPayment() {
        return monthlyPayment;
    }

    public void setMonthlyPayment(Float monthlyPayment) {
        this.monthlyPayment = monthlyPayment;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public Float getPI() {
        return PI;
    }

    public void setPI(Float PI) {
        this.PI = PI;
    }

    public Float getPropertyTaxMonthly() {
        return propertyTaxMonthly;
    }

    public void setPropertyTaxMonthly(Float propertyTaxMonthly) {
        this.propertyTaxMonthly = propertyTaxMonthly;
    }

    public Integer getMonthlyDueDay() {
        return monthlyDueDay;
    }

    public void setMonthlyDueDay(Integer monthlyDueDay) {
        this.monthlyDueDay = monthlyDueDay;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }
}
