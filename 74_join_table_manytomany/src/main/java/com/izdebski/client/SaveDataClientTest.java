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
            Employee employee1= new Employee();
            employee1.setEmployeeName("Peter Petrenko");
            employee1.setEmail("peter.pt2031@gmail.com");
            employee1.setSalary(50000.00);
            employee1.setDoj(new Date());

            Employee employee2= new Employee();
            employee2.setEmployeeName("Roman Romanenko");
            employee2.setEmail("roman.rm2031@gmail.com");
            employee2.setSalary(70000.00);
            employee2.setDoj(new Date());

            Address address1 = new Address();
            address1.setCity("Lviv");
            address1.setPincode(9087727L);
            address1.setState("Ukraine");
            address1.setStreet("Park Street");

            Address address2 = new Address();
            address2.setCity("Ternopil");
            address2.setPincode(9000027L);
            address2.setState("MH");
            address2.setStreet("XYZ Street");

            Address address3 = new Address();
            address3.setCity("Delhi");
            address3.setPincode(908877027L);
            address3.setState("DL");
            address3.setStreet("PQR Street");

            employee1.getAddressList().add(address1);
            employee1.getAddressList().add(address2);
            employee1.getAddressList().add(address3);

            address1.getEmpList().add(employee1);
            address2.getEmpList().add(employee1);
            address3.getEmpList().add(employee1);

            employee2.getAddressList().add(address2);
            employee2.getAddressList().add(address3);

            address2.getEmpList().add(employee2);
            address3.getEmpList().add(employee2);

            session.persist(employee1);
            session.persist(employee2);

            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }
}