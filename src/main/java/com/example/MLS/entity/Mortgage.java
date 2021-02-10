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
    private Float totalInitalLoan;

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

    @Column(name = "monthly_due_day", length = 25)
    private Integer monthlyDueDay;

    @Column(name = "bank_name", length = 64)
    private Long bankName;

}
