package com.izdebski.client;

import com.izdebski.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

public class HQLHibernate5ClientTest {

    public static void main(String[] args) {

        SessionFactory sf = HibernateUtil.getSessionFactory();
        insertEmployeeRecords(sf);
    }

    private static void insertEmployeeRecords(SessionFactory sf) {

        try(Session session = sf.openSession() ) {

            String HQL = "INSERT INTO Employee(employeeName,doj,salary,bonus,email,designation)"+
                    "SELECT employeeName,doj,salary,bonus,email,designation FROM BackupEmployee";

            Query query = session.createQuery(HQL);
            session.beginTransaction();
            int executeUpdate = query.executeUpdate();
            if(executeUpdate>0)
                System.out.println(executeUpdate+" records are inserted successfully..");
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}