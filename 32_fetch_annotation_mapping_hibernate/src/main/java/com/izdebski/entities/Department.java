package com.izdebski.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name="department")
public class Department {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="dept_name",length=100,nullable=false)
    private String deptName;

    //Make sure that only one Strategy you are applying at a time.
    //@Fetch(value = FetchMode.SELECT)// Run HibernateSelectAndSubselectFetchModeTest.java class to check this fetching Strategy
    //@Fetch(value = FetchMode.SUBSELECT)// Run HibernateSelectAndSubselectFetchModeTest.java class to check this fetching Strategy
    @Fetch(value = FetchMode.JOIN)//// Run HibernateJoinFetchModeTest.java class to check this fetching Strategy
    @OneToMany(mappedBy="department",cascade=CascadeType.PERSIST)
    private List<Employee> employees = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }
}
