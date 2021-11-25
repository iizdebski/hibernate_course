package com.izdebski.client;

import java.util.Date;

import com.izdebski.entities.Employee;
import com.izdebski.util.HibernateUtil;
import org.hibernate.Session;

public class SaveDataClientTest {

    public static void main(String[] args) {
        Employee employee1= new Employee();
        employee1.setEmployeeName("Martin Bingel");
        employee1.setEmail("martin.mb2031@gmail.com");
        employee1.setSalary(50000.00);
        employee1.setDoj(new Date());

        Employee employee2= new Employee();
        employee2.setEmployeeName("Sean Connery");
        employee2.setEmail("sean.c2031@gmail.com");
        employee2.setSalary(40000.00);
        employee2.setDoj(new Date());

        Employee employee3= new Employee();
        employee3.setEmployeeName("Barry Johnson");
        employee3.setEmail("barry.b2031@gmail.com");
        employee3.setSalary(60000.00);
        employee3.setDoj(new Date());

        try(Session session= HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();

            session.save(employee1);
            session.save(employee2);
            session.save(employee3);

            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}