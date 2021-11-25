package com.izdebski.client;

import com.izdebski.entities.Address;
import com.izdebski.entities.Employee;
import com.izdebski.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;

public class FetchDataClient {

    public static void main(String[] args) {
        //getEmployeeAndAdressByEmployeeId();
        getEmployeeAndAdressByAddressId();

    }

    private static void getEmployeeAndAdressByAddressId() {

        try(Session session = HibernateUtil.getSessionFactory().openSession()) {

            Address address = session.get(Address.class, 1);
            System.out.println(address);
            //System.out.println(address.getEmployee());
        } catch (HibernateException e) {
            e.printStackTrace();
        }

    }

    private static void getEmployeeAndAdressByEmployeeId() {
        Employee employee = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            employee = session.get(Employee.class, 1);
            System.out.println(employee);
            Address address = employee.getAddress();
            System.out.println(address);
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }
}