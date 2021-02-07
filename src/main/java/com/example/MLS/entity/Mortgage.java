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
//    @Column(name = "house_id", length = 64)
//    private Long houseId;

    @Column(name = "total_initial_loan", nullable = false, length = 64)
    private Integer totalInitalLoan;

    @Column(name = "first_payment_date", nullable = false, length = 64)
    private Date firstPaymentDate;

    @Column(name = "interest_rate", nullable = false, length = 3)
    private Integer interestRate;

    @Column(name = "length", nullable = false, length = 3)
    private Long length;

    @Column(name = "house_id", length = 64)
    private Long houseId;


}
