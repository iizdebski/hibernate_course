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
            employee.setEmployeeName("Peter Nechyporenko");
            employee.setEmail("sydir.sd2031@gmail.com");
            employee.setSalary(50000.00);
            employee.setDoj(new Date());

            Address address1 = new Address();
            address1.setCity("Chernivtsi");
            address1.setPincode(9087727L);
            address1.setState("Bukovyna");
            address1.setStreet("Park Street");

            Address address2 = new Address();
            address2.setCity("Franyk");
            address2.setPincode(9000027L);
            address2.setState("MH");
            address2.setStreet("XYZ Street");

            employee.getAddressList().add(address1);
            employee.getAddressList().add(address2);
            session.persist(employee);

            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }
}