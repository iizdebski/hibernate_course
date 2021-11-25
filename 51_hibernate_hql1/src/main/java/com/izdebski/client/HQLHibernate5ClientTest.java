package com.izdebski.client;

import java.util.List;

import com.izdebski.entities.Employee;
import com.izdebski.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

public class HQLHibernate5ClientTest {

    public static void main(String[] args) {

        SessionFactory sf = HibernateUtil.getSessionFactory();
        //getAllEmployees(sf);
        //getEmployeeById(sf);
        getEmployeeByIdAndEmail(sf);
        //getAllEmployeesName(sf);
        //getAllEmployeesIdAndName(sf);
    }

    private static void getAllEmployeesIdAndName(SessionFactory sf) {

        try(Session session = sf.openSession() ) {
            String HQL = "SELECT employeeId,employeeName FROM Employee";
            Query query = session.createQuery(HQL);
            List<Object[]> list = query.list();
            for (Object[] objects : list) {
                Integer employeeId =(Integer)objects[0];
                String employeeName =(String)objects[1];
                System.out.println(employeeId+"\t"+employeeName);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void getAllEmployeesName(SessionFactory sf) {

        try(Session session = sf.openSession() ) {
            String HQL = "SELECT employeeName FROM Employee";

            Query<String> query = session.createQuery(HQL);
            query.list().forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void getEmployeeByIdAndEmail(SessionFactory sf) {

        try(Session session = sf.openSession() ) {
            int empId=2;
            String email="martin.b2017@gmail.com";
            String HQL = "FROM Employee WHERE employeeId=:empId AND email=:email";
            Query<Employee> query = session.createQuery(HQL, Employee.class);
            query.setParameter("email", email);
            query.setParameter("empId", empId);

            Employee employee = query.uniqueResult();
            System.out.println(employee);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void getEmployeeById(SessionFactory sf) {
        int empId=2;
        try(Session session = sf.openSession() ) {
            String HQL = "FROM Employee WHERE employeeId=?";
            Query<Employee> query = session.createQuery(HQL, Employee.class);
            query.setParameter(0, empId);

            Employee employee = query.uniqueResult();
            System.out.println(employee);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static void getAllEmployees(SessionFactory sf) {
        try(Session session = sf.openSession() ) {

            String HQL = "FROM Employee";
            //String HQL ="FROM com.izdebski.entities.Employee";
            Query<Employee> query = session.createQuery(HQL, Employee.class);
            List<Employee> list = query.list();
            list.forEach(System.out::println);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}