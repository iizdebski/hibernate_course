package com.izdebski.client;

import com.izdebski.entities.Employee;
import com.izdebski.util.HibernateUtil;
import org.hibernate.Session;

public class DirtyCheckingClientTest {

    public static void main(String[] args) {
        Session session = null;
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            Employee employee = session.get(Employee.class, 1);
            if(employee != null){
                session.beginTransaction();
                employee.setSalary(40000.00);
                employee.setEmployeeName("Martin Bingel2");
                session.update(employee);
                session.getTransaction().commit();
                //employee.setEmployeeName("Martin Bingel2");
           }else{
                System.out.println("Employee doesn't exist with provided Id");
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(session != null){
                session.close();
            }
        }
    }
}