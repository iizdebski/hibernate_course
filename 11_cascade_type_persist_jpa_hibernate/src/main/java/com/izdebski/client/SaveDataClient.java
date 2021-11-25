package com.izdebski.client;

import java.util.Date;

import com.izdebski.entities.Address;
import com.izdebski.entities.Employee;
import com.izdebski.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;

public class SaveDataClient {

    public static void main(String[] args) {
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            Employee employee= new Employee();
            employee.setEmployeeName("Peter Petrenko");
            employee.setEmail("peter.pt2031@gmail.com");
            employee.setSalary(50000.00);
            employee.setDoj(new Date());

            Address address1 = new Address();
            address1.setCity("Lviv");
            address1.setPincode(9087727L);
            address1.setState("Ukraine");
            address1.setStreet("Park Street");

            employee.setAddress(address1);
            session.persist(employee);

            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }
}