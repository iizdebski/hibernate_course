package com.izdebski.client;

import com.izdebski.entities.Employee;
import com.izdebski.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;

public class DeleteDataClient {

    public static void main(String[] args) {
        deleteEmployeeById();

    }

    private static void deleteEmployeeById() {

        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            Employee employee = session.get(Employee.class, 2);
            if(employee!= null){
                session.beginTransaction();
                session.delete(employee);
                session.getTransaction().commit();
            }

        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }
}