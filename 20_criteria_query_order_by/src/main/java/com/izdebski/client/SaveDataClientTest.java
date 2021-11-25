package com.izdebski.client;

import org.hibernate.Session;

import com.izdebski.entities.Employee;
import com.izdebski.util.HibernateUtil;

public class SaveDataClientTest {

    public static void main(String[] args) {

        try(Session session = HibernateUtil.getSessionFactory().openSession() ) {

            Employee employee1 = new Employee();
            employee1.setDesignation("Developer");
            employee1.setName("Sean Connery");
            employee1.setSalary(70000.00);

            Employee employee2 = new Employee();
            employee2.setDesignation("Sr. Developer");
            employee2.setName("Martin Petersson");
            employee2.setSalary(50000.00);

            Employee employee3 = new Employee();
            employee3.setDesignation("Team Lead");
            employee3.setName("Barry Johnson");
            employee3.setSalary(90000.00);

            Employee employee4 = new Employee();
            employee4.setDesignation("Associate");
            employee4.setName("Arnold");
            employee4.setSalary(80000.00);

            Employee employee5 = new Employee();
            employee5.setDesignation("Sr. Associate");
            employee5.setName("Harry Johnson ");
            employee5.setSalary(60000.00);

            Employee employee6 = new Employee();
            employee6.setDesignation("AVP");
            employee6.setName("Jodi Albert");
            employee6.setSalary(980000.00);

            Employee employee7 = new Employee();
            employee7.setDesignation("VP");
            employee7.setName("Anthony Andrews");
            employee7.setSalary(100000.00);

            session.beginTransaction();

            session.save(employee1);
            session.save(employee2);
            session.save(employee3);
            session.save(employee4);
            session.save(employee5);
            session.save(employee6);
            session.save(employee7);

            session.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}