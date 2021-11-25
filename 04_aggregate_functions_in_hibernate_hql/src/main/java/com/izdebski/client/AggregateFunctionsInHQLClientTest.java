package com.izdebski.client;

import java.util.List;

import com.izdebski.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

public class AggregateFunctionsInHQLClientTest {

    public static void main(String[] args) {
        try(Session session= HibernateUtil.getSessionFactory().openSession()) {

            String HQL="SELECT COUNT(e),AVG(e.salary),MIN(e.salary),SUM(e.salary) FROM Employee e";

            Query<Object[]> query = session.createQuery(HQL, Object[].class);
            List<Object[]> list = query.list();
            for (Object[] objects : list) {
                Long empCount=(Long)objects[0];
                Double avgSal=(Double)objects[1];
                Double minSal=(Double)objects[2];
                Double sumOfSal=(Double)objects[3];
                System.out.println("Total no. of Employees:"+empCount);
                System.out.println("Avg salary:"+avgSal);
                System.out.println("Min Salary:"+minSal);
                System.out.println("Sum of Salary:"+sumOfSal);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}