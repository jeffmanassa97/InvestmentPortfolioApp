package com.example.MLS.entity;

import javax.persistence.*;

@Entity
@Table(name = "house")
public class House {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "users")
    private User user;
//    @Column(name = "user_id",nullable = false, length = 64)
//    private Long userId;

    @OneToOne(mappedBy = "house", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Mortgage mortgage;
//    @Column(name = "mortgage_id",nullable = false, length = 64)
//    private Long mortgageId;

    @Column(name = "nickname",nullable = false, length = 64)
    private String nickName;

    @Column(name = "year_built", nullable = true, length = 4)
    private Integer yearBuilt;

    @Column(name = "square_feet",nullable = true, length = 64)
    private Integer squareFeet;

    @Column(name = "purchase_price",nullable = true, length = 64)
    private Integer purchasePrice;

    @Column(name = "down_payment_amount",nullable = true, length = 64)
    private Integer downPaymentAmount;

    @Column(name = "closing_date",nullable = true, length = 64)
    private Integer closingDate;


    @Column(name = "num_beds",nullable = true, length = 64)
    private Integer numBeds;

    @Column(name = "num_full_baths",nullable = true, unique = true, length = 45)
    private Integer numFullBaths;

    @Column(name = "num_half_baths",nullable = true, length = 64)
    private Integer numHalfBaths;

    @Column(name = "ceiling_height", nullable = true, length = 20)
    private String ceilingHeight;

    @Column(name = "last_name", nullable = true, length = 20)
    private String lastName;


}