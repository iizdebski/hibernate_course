package com.izdebski.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name="address")
public class Address implements Serializable {

    @Id
    @Column(name = "address_id")
    @GeneratedValue
    private Integer addressId;

    @Column(name = "street_name", length = 50)
    private String street;
    @Column(name = "city_name", length = 50)
    private String city;
    @Column(name = "state_name")
    private String state;
    @Column(name = "pin_code")
    private Long pincode;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "address", cascade = CascadeType.ALL)
    private List<Employee> employee;

    public Address(){}

    public Address(String street, String city, String state, Long pincode, List<Employee> employee) {
        this.street = street;
        this.city = city;
        this.state = state;
        this.pincode = pincode;
    }

    public Integer getAddressId() {
        return addressId;
    }

    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Long getPincode() {
        return pincode;
    }

    public void setPincode(Long pincode) {
        this.pincode = pincode;
    }

    @Override
    public String toString() {
        return "Address{" +
                "addressId=" + addressId +
                ", street='" + street + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", pincode=" + pincode +
                '}';
    }
}