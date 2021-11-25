package com.izdebski.client;

import com.izdebski.entities.Employee;
import com.izdebski.util.HibernateUtil;
import org.hibernate.Session;

public class SecondLevelCacheClientTest {

    public static void main(String[] args) throws Exception {
        Session session = null;
        try {
            Long employeeId = 1L;
            session = HibernateUtil.getSessionFactory().openSession();
            Employee employee1 = session.get(Employee.class, employeeId);
            System.out.println(employee1);

            System.out.println("-------------------------------------------------------------");
        } catch (Exception e) {
            throw e;
        } finally {
            if (session != null) {
                session.close();
            }
        }

        try {
            Long employeeId = 1L;
            session = HibernateUtil.getSessionFactory().openSession();
            Employee employee1 = session.get(Employee.class, employeeId);
            System.out.println(employee1);
        } catch (Exception e) {
            throw e;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}