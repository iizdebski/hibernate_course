package com.izdebski.client;

import java.util.List;

import com.izdebski.entities.Department;
import com.izdebski.entities.Employee;
import com.izdebski.util.HibernateUtil;
import org.hibernate.Session;

public class NPulsOneSelectProblemFixedUsingCriteriaQueryTest {

    public static void main(String[] args) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            //List<Department> departments = session.createQuery("From Department", Department.class).getResultList();
            List<Department> departments = session.createQuery("From Department d JOIN fetch d.employees", Department.class).getResultList();

            for (Department department : departments) {
                System.out.println("Department details:::::");

                System.out.println(department.getId()+"\t"+department.getDeptName());
                List<Employee> employees = department.getEmployees();
                System.out.println("Employees details::::::");
                for (Employee employee : employees) {

                    System.out.println(employee.getId() + "\t" + employee.getEmployeeName() + "\t" + employee.getUsername()
                            + "\t" + employee.getPassword() + "\t" + employee.getAccessLevel());
                }

            }
        }
    }
}