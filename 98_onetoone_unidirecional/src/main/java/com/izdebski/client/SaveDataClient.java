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
            employee.setEmployeeName("Barry Bingel");
            employee.setEmail("barry.cs2017@gmail.com");
            employee.setSalary(50000.00);
            employee.setDoj(new Date());

            Address address1 = new Address();
            address1.setCity("Chennai");
            address1.setPincode(9087727L);
            address1.setState("Tamilnadu");
            address1.setStreet("Park Street");

            employee.setAddress(address1);
            //Integer id =(Integer)session.save(employee);
            //System.out.println("Employee is created  with Id::"+id);
            //session.save(address1);
            //session.save(employee);

            session.persist(employee);

            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }
}