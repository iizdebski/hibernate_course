package com.izdebski.client;

import java.math.BigDecimal;
import java.util.Date;

import com.izdebski.entities.Employee;
import com.izdebski.util.HibernateUtil;
import org.hibernate.Session;

public class SaveDataClientTest {

    public static void main(String[] args) {

        try (Session session= HibernateUtil.getSessionFactory().openSession()){
            session.beginTransaction();
            Employee employee1 = new Employee();
            employee1.setEmployeeName("Sean Murphy");
            employee1.setDesignation("IT Manager");
            employee1.setDoj(new Date());
            employee1.setEmail("sean.m2031@yahoo.com");
            employee1.setBonus(new BigDecimal(2000));
            employee1.setSalary(90000.00);

            Employee employee2 = new Employee();
            employee2.setEmployeeName("Martin Bingel");
            employee2.setDesignation("IT Developer");
            employee2.setDoj(new Date());
            employee2.setEmail("martin.b2031@gmail.com");
            employee2.setBonus(new BigDecimal(1000));
            employee2.setSalary(40000.00);

            session.save(employee1);
            session.save(employee2);

            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}