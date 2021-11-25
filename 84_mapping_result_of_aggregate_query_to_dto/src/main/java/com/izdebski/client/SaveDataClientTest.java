package com.izdebski.client;

import org.hibernate.Session;

import com.izdebski.entities.Department;
import com.izdebski.entities.Employee;
import com.izdebski.util.HibernateUtil;

public class SaveDataClientTest {

    public static void main(String[] args) {

        try(Session session = HibernateUtil.getSessionFactory().openSession() ) {

            Department department1 = new Department();
            department1.setLocation("London");
            department1.setName("IT");

            Employee employee1 = new Employee();
            employee1.setDesignation("Developer");
            employee1.setName("Sean Murphy");
            employee1.setSalary(70000.00);
            department1.getEmployees().add(employee1);
            employee1.setDepartment(department1);

            Employee employee2 = new Employee();
            employee2.setDesignation("Sr. Developer");
            employee2.setName("Martin Bingel");
            employee2.setSalary(50000.00);
            department1.getEmployees().add(employee2);
            employee2.setDepartment(department1);

            Employee employee3 = new Employee();
            employee3.setDesignation("Team Lead");
            employee3.setName("Barry Johnson");
            employee3.setSalary(90000.00);
            department1.getEmployees().add(employee3);
            employee3.setDepartment(department1);

            Department department2 = new Department();
            department2.setLocation("Delhi");
            department2.setName("Finance");

            Employee employee4 = new Employee();
            employee4.setDesignation("Associate");
            employee4.setName("Arnold");
            employee4.setSalary(80000.00);
            department2.getEmployees().add(employee4);
            employee4.setDepartment(department2);

            Employee employee5 = new Employee();
            employee5.setDesignation("Sr. Associate");
            employee5.setName("Harry Johnson ");
            employee5.setSalary(60000.00);
            department2.getEmployees().add(employee5);
            employee5.setDepartment(department2);

            Employee employee6 = new Employee();
            employee6.setDesignation("AVP");
            employee6.setName("Jodi Albert");
            employee6.setSalary(980000.00);
            department2.getEmployees().add(employee6);
            employee6.setDepartment(department2);

            Employee employee7 = new Employee();
            employee7.setDesignation("VP");
            employee7.setName("Anthony Andrews");
            employee7.setSalary(100000.00);
            department2.getEmployees().add(employee7);
            employee7.setDepartment(department2);

            session.beginTransaction();

            session.save(department1);
            session.save(department2);

            session.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}