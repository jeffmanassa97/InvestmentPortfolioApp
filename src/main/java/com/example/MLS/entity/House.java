package com.example.MLS.entity;

import javax.persistence.*;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "house")
public class House {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "house_id")
    private Long houseId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User users;

    @OneToMany(mappedBy = "house", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Image> images = new HashSet<>();

    @OneToOne(mappedBy = "house", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Mortgage mortgage;

    @Column(name = "address", nullable = false, length = 64)
    private String address;
    @Column(name = "state", nullable = false, length = 64)
    private String state;
    @Column(name = "zip_code", nullable = false, length = 64)
    private String zipCode;

    @Column(name = "purchase_price", nullable = false)
    private Integer purchasePrice;

    @Column(name = "down_payment_amount", nullable = false)
    private Float downPaymentAmount;

    @Column(name = "closing_date")
    private Date closingDate;

    /**
     * House specs.
     */

    @Column(name = "num_beds")
    private Integer numBeds;

    @Column(name = "num_full_baths")
    private Integer numFullBaths;

    @Column(name = "num_half_baths")
    private Integer numHalfBaths;

    @Column(name = "year_built")
    private Integer yearBuilt;

    @Column(name = "square_feet")
    private Integer squareFeet;

    /**
     * Manufacture dates of key items.
     */

    @Column(name = "date_HVAC")
    private Date dateHVAC;

    @Column(name = "date_roof")
    private Date dateRoof;

    @Column(name = "date_plumbing")
    private Date datePlumbing;

    @Column(name = "date_water_heater")
    private Date dateWaterHeater;

    /**
     * Income.
     */

    @Column(name = "monthly_rent", nullable = false, scale = 2)
    private Float monthlyRent;

    /**
     * Misc.
     */

    @Column(name = "notes", nullable = true, length = 400)
    private String notes;


    /**
     * Setters and Getters.
     */
    public House() {}

    public Long getId() {
        return houseId;
    }

    public void setId(Long houseId) {
        this.houseId = houseId;
    }

    public User getUser() {
        return users;
    }

    public void setUser(User users) {
        this.users = users;
    }

    public Set<Image> getImages() {
        return images;
    }

    public void setImages(Set<Image> images) {
        this.images = images;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public Mortgage getMortgage() {
        return mortgage;
    }

    public void setMortgage(Mortgage mortgage) {
        this.mortgage = mortgage;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipcode() {
        return zipCode;
    }

    public void setZipcode(String zipCode) {
        this.zipCode = zipCode;
    }

    public Integer getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(Integer purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public Float getDownPaymentAmount() {
        return downPaymentAmount;
    }

    public void setDownPaymentAmount(Float downPaymentAmount) {
        this.downPaymentAmount = downPaymentAmount;
    }

    public Date getClosingDate() {
        return closingDate;
    }

    public void setClosingDate(Date closingDate) {
        this.closingDate = closingDate;
    }

    public Integer getNumBeds() {
        return numBeds;
    }

    public void setNumBeds(Integer numBeds) {
        this.numBeds = numBeds;
    }

    public Integer getNumFullBaths() {
        return numFullBaths;
    }

    public void setNumFullBaths(Integer numFullBaths) {
        this.numFullBaths = numFullBaths;
    }

    public Integer getNumHalfBaths() {
        return numHalfBaths;
    }

    public void setNumHalfBaths(Integer numHalfBaths) {
        this.numHalfBaths = numHalfBaths;
    }

    public Integer getYearBuilt() {
        return yearBuilt;
    }

    public void setYearBuilt(Integer yearBuilt) {
        this.yearBuilt = yearBuilt;
    }

    public Integer getSquareFeet() {
        return squareFeet;
    }

    public void setSquareFeet(Integer squareFeet) {
        this.squareFeet = squareFeet;
    }

    public Date getDateHVAC() {
        return dateHVAC;
    }

    public void setDateHVAC(Date dateHVAC) {
        this.dateHVAC = dateHVAC;
    }

    public Date getDateRoof() {
        return dateRoof;
    }

    public void setDateRoof(Date dateRoof) {
        this.dateRoof = dateRoof;
    }

    public Date getDatePlumbing() {
        return datePlumbing;
    }

    public void setDatePlumbing(Date datePlumbing) {
        this.datePlumbing = datePlumbing;
    }

    public Date getDateWaterHeater() {
        return dateWaterHeater;
    }

    public void setDateWaterHeater(Date dateWaterHeater) {
        this.dateWaterHeater = dateWaterHeater;
    }

    public Float getMonthlyRent() {
        return monthlyRent;
    }

    public void setMonthlyRent(Float monthlyRent) {
        this.monthlyRent = monthlyRent;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}