package com.izdebski.entities;

import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="employee")
@DynamicUpdate
public class Employee {

    @Id
    @Column(name="employee_id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer employeeId;

    @Column(name="employee_name",length=200,nullable=false)
    private String employeeName;

    @Column(name="email")
    private String email;

    @Column(name="date_of_join")
    private Date doj;

    @Column(name="salary")
    private Double salary;

    @OneToMany(cascade=CascadeType.ALL)
    @JoinTable(name="employee_address_table",joinColumns=@JoinColumn(name="employee_id"),inverseJoinColumns=@JoinColumn(name="address_id"))
    private List<Address> addressList = new ArrayList<>();

    public Integer getEmployeeId() {
        return employeeId;
    }
    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }
    public String getEmployeeName() {
        return employeeName;
    }
    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public Date getDoj() {
        return doj;
    }
    public void setDoj(Date doj) {
        this.doj = doj;
    }
    public Double getSalary() {
        return salary;
    }
    public void setSalary(Double salary) {
        this.salary = salary;
    }
    public void setAddressList(List<Address> addressList) {
        this.addressList = addressList;
    }
    public List<Address> getAddressList() {
        return addressList;
    }
    @Override
    public String toString() {
        return "Employee [employeeId=" + employeeId + ", employeeName=" + employeeName + ", email=" + email + ", date_of_join="
                + doj + ", salary=" + salary + "]";
    }
}