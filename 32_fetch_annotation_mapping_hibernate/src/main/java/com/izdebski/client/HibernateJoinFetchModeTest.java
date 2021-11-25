package com.izdebski.client;

import java.util.List;

import org.hibernate.Session;

import com.izdebski.entities.Department;
import com.izdebski.entities.Employee;
import com.izdebski.util.HibernateUtil;

public class HibernateJoinFetchModeTest {

    public static void main(String[] args) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            Long departmentId = 1L;
            Department department = session.get(Department.class, departmentId);
            if(department != null){
                System.out.println("Department ID:"+department.getId());
                List<Employee> employees = department.getEmployees();
                System.out.println("Employees count:" + employees.size());
            }else{
                System.out.println("Department details not found with ID: "+departmentId);
            }
        }
    }
}