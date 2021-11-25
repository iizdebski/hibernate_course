package com.izdebski.client;

import java.util.List;

import com.izdebski.entities.Department;
import com.izdebski.entities.Employee;
import com.izdebski.util.HibernateUtil;
import org.hibernate.Session;

public class BatchFetchingClientTest {

    public static void main(String[] args) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // Btach fetching example
            List<Department> departments = session.createQuery(
                    "FROM Department", Department.class)
                    .getResultList();

            for ( Department department : departments ) {
                System.out.println("Department details:::::");
                System.out.println(department.getId() + "\t" + department.getDeptName());
                System.out.println("Employees details::::::");
                List<Employee> employees = department.getEmployees();
                for (Employee employee : employees) {
                    System.out.println(employee.getId() + "\t" + employee.getEmployeeName() + "\t" + employee.getUsername()
                            + "\t" + employee.getPassword() + "\t" + employee.getAccessLevel());
                }
            }
        }
    }
}