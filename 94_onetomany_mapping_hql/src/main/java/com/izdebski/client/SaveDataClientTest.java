package com.izdebski.client;

import java.util.Date;

import com.izdebski.entities.Address;
import com.izdebski.entities.Employee;
import com.izdebski.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;

public class SaveDataClientTest {

    public static void main(String[] args) {
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            Employee employee= new Employee();
            employee.setEmployeeName("Peter Petrenko");
            employee.setEmail("peter.pt2019@gmail.com");
            employee.setSalary(50000.00);
            employee.setDoj(new Date());

            Address address1 = new Address();
            address1.setCity("Ternopil");
            address1.setPincode(9087727L);
            address1.setState("Galicia");
            address1.setStreet("Park St.");

            Address address2 = new Address();
            address2.setCity("Lviv");
            address2.setPincode(9000027L);
            address2.setState("UA");
            address2.setStreet("XYZ St.");

            employee.getAddressList().add(address1);
            employee.getAddressList().add(address2);

            address1.setEmployee(employee);
            address2.setEmployee(employee);

            session.persist(employee);

            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }
}