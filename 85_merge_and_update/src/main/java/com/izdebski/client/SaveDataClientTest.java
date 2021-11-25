package com.izdebski.client;

import java.util.Date;

import com.izdebski.entities.Employee;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import com.izdebski.util.HibernateUtil;

public class SaveDataClientTest {

    public static void main(String[] args) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            Employee employee = new Employee();
            employee.setEmployeeName("Roman Petrenko");
            employee.setEmail("roman.pt2031@gmail.com");
            employee.setSalary(50000.00);
            employee.setDoj(new Date());
            session.save(employee);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }
}