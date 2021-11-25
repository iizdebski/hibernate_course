package com.izdebski.client;

import java.util.List;

import com.izdebski.entities.Employee;
import com.izdebski.util.HibernateUtil;
import org.hibernate.Session;

public class QueryCacheClientTest {

    public static void main(String[] args) throws Exception {
        Session session = null;
        String SELECT_EMPLOYEE_HQL = "FROM Employee";
        try {
            // Get employee list from DATABASE
            System.out.println("Get employee list from DATABASE:::::::::::::::::::::");
            session = HibernateUtil.getSessionFactory().openSession();

            @SuppressWarnings("unchecked")
            List<Employee> employees = session.createQuery(SELECT_EMPLOYEE_HQL).setCacheable(true)
                    .setCacheRegion("employee.cache").list();
            for (Employee employee : employees) {
                System.out.println("Employee Name : " + employee.getEmployeeName());
                System.out.println("Employee UserName : " + employee.getUsername());
                System.out.println("Employee Passsword : " + employee.getPassword());
                System.out.println("Employee AccessLevel : " + employee.getAccessLevel());

                System.out.println("--------------------------------------------------------------");
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (session != null) {
                session.close();
            }
        }

        try {

            /// Get employee list from Cached result
            System.out.println(" Get employee list from Cached result::::::::::::::::::::");
            session = HibernateUtil.getSessionFactory().openSession();

            @SuppressWarnings("unchecked")
            List<Employee> employees = session.createQuery(SELECT_EMPLOYEE_HQL).setCacheable(true)
                    .setCacheRegion("employee.cache").list();
            for (Employee employee : employees) {
                System.out.println("Employee Name : " + employee.getEmployeeName());
                System.out.println("Employee UserName : " + employee.getUsername());
                System.out.println("Employee Passsword : " + employee.getPassword());
                System.out.println("Employee AccessLevel : " + employee.getAccessLevel());
            }

        } catch (Exception e) {
            throw e;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}