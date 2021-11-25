package com.izdebski.hibernate;

import com.izdebski.model.Student;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.List;

public class StudentsDao {

    public List<Student> getStudentList(){

        Session session = null;
        List<Student> empList = null;
        try {
            session = HibernateUtil.getSession();
            String queryStr = "select emp from Student emp";
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

    public static void main(String a[]) {

        StudentsDao empDao = new StudentsDao();
        System.out.println("---------------------------");

        List<Student> empList = empDao.getStudentList();
        System.out.println("emp size: "+empList.size());
        empList.stream().forEach(System.out::println);

        System.out.println("---------------------------");
    }
}