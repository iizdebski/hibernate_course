package com.izdebski.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="EMPLOYEES")
public class Employee implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="EMP_ID", unique = true, nullable = false)
    private Long empId;

    private String name;

    private String department;

    private Long salary;

    @Column(name="JOINED_ON")
    private Date joinedOn;

    @ManyToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
    @JoinTable(
            name = "EMP_ASSIGNMENTS",
            joinColumns = { @JoinColumn(name = "EMP_ID") },
            inverseJoinColumns = { @JoinColumn(name = "PR_ID") }
    )
    private List<Project> empAssignmentList;

    public Long getEmpId() {
        return empId;
    }

    public void setEmpId(Long empId) {
        this.empId = empId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Long getSalary() {
        return salary;
    }

    public void setSalary(Long salary) {
        this.salary = salary;
    }

    public Date getJoinedOn() {
        return joinedOn;
    }

    public void setJoinedOn(Date joinedOn) {
        this.joinedOn = joinedOn;
    }

    public List<Project> getEmpAssignmentList() {
        return empAssignmentList;
    }

    public void setEmpAssignmentList(List<Project> empAssignmentList) {
        this.empAssignmentList = empAssignmentList;
    }

    @Override
    public String toString() {

        String resp = this.empId+" | "+this.name+" | "+this.department+" | "+this.salary+" | "+this.joinedOn;

        return resp;
    }
}