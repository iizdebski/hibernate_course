package com.izdebski.client;

import com.izdebski.entities.Employee;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import com.izdebski.util.HibernateUtil;

public class MergeUpdateClientTest {

    public static void main(String[] args) {
        Employee employee1 = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            employee1 = session.get(Employee.class, 1);
            System.out.println(employee1);
        } catch (HibernateException e) {
            e.printStackTrace();
        }

        employee1.setSalary(95000.00);
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            Employee employee2 = session.get(Employee.class, 1);
            session.merge(employee1);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }
}