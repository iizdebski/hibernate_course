package com.izdebski.client;

import com.izdebski.entities.Address;
import com.izdebski.entities.Employee;
import com.izdebski.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

public class HQLClientTest {

    public static void main(String[] args) {
        getEmployeeAndAdressByEmployeeId();
        //getEmployeeAndAdressByAddressId();
    }

    private static void getEmployeeAndAdressByAddressId() {

        try(Session session = HibernateUtil.getSessionFactory().openSession()) {

            String HQL="FROM Address addr LEFT OUTER JOIN FETCH addr.employee WHERE addr.addressId=:addrId";
            Address address = session.createQuery(HQL, Address.class).setParameter("addrId", 1).uniqueResult();
            System.out.println(address);
            System.out.println(address.getEmployee());
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    private static void getEmployeeAndAdressByEmployeeId() {
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            String HQL="FROM Employee emp LEFT JOIN FETCH emp.address WHERE  emp.employeeId=:empId";
            Query<Employee> query = session.createQuery(HQL, Employee.class);
            query.setParameter("empId", 1);
            Employee employee = query.uniqueResult();
            System.out.println(employee);
            Address address = employee.getAddress();
            System.out.println(address);
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }
}