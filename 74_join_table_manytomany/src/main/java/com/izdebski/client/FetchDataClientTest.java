package com.izdebski.client;

import com.izdebski.entities.Employee;
import com.izdebski.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;


public class FetchDataClientTest {

    public static void main(String[] args) {
        getEmployeeAndAdressByEmployeeId();

    }

    private static void getEmployeeAndAdressByEmployeeId() {
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            Employee employee2 = session.get(Employee.class , 1);
            System.out.println(employee2);
            if(employee2 != null){
                employee2.getAddressList().forEach(System.out::println);
            }
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }
}