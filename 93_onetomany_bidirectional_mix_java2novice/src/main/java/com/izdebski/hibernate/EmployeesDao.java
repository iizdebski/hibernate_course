package com.izdebski.hibernate;

import com.izdebski.entities.Employee;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.List;

public class EmployeesDao {

    public List<Employee> getStudentList(){


        Session session = null;
        List<Employee> empList = null;
        try {
            session = HibernateUtil.getSession();
            String queryStr = "select emp from Employee emp";
            Query query = session.createQuery(queryStr);
            empList = query.list();
        } catch(Exception ex) {
            ex.printStackTrace();
            // handle exception here
        } finally {
            try {if(session != null) session.close();} catch(Exception ex) {}
        }
        return empList;
    }

    public static void main(String[] args) {

        EmployeesDao empDao = new EmployeesDao();
        System.out.println("---------------------------");

        List<Employee> empList = empDao.getStudentList();
        System.out.println("emp size: "+empList.size());
        empList.stream().forEach(System.out::println);

        System.out.println("---------------------------");
    }
}