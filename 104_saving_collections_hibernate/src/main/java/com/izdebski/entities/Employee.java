package com.izdebski.entities;

import com.izdebski.model.Address;
import org.hibernate.annotations.CollectionId;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

@Entity
@Table(name="employee_table")
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

    @Column(name="date_of_joing")
    private Date doj;

    @Column(name="salary")
    private Double salary;

    @ElementCollection(fetch=FetchType.EAGER)
    @JoinTable(name="address_table",joinColumns=@JoinColumn(name="employee_id"))
    @GenericGenerator(name = "sequence-gen", strategy ="sequence")
    @CollectionId(columns = {@Column(name="address_id")}, generator = "sequence-gen", type = @Type(type = "int"))
    private Collection<Address> addressList = new ArrayList<>();

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

    public Collection<Address> getAddressList() {
        return addressList;
    }

    public void setAddressList(Collection<Address> addressList) {
        this.addressList = addressList;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "employeeId=" + employeeId +
                ", employeeName='" + employeeName + '\'' +
                ", email='" + email + '\'' +
                ", doj=" + doj +
                ", salary=" + salary +
                ", addressList=" + addressList +
                '}';
    }
}