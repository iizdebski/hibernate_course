package com.izdebski.client;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.izdebski.entities.Department;
import com.izdebski.entities.Employee;
import com.izdebski.util.HibernateUtil;

public class CriteriaQueryGroupByAndHavingClientTest {

    public static void main(String[] args) {


        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            CriteriaBuilder builder = session.getCriteriaBuilder();

            CriteriaQuery<Object[]> criteriaQuery = builder.createQuery(Object[].class);
            Root<Employee> root = criteriaQuery.from(Employee.class);

            criteriaQuery.multiselect(builder.count(root.get("name")),builder.sum(root.get("salary")),root.get("department"));

            criteriaQuery.groupBy(root.get("department"));
            criteriaQuery.having(builder.greaterThan(builder.sum(root.get("salary")), 200000.00));
            Query<Object[]> query = session.createQuery(criteriaQuery);

            List<Object[]> resultList = query.getResultList();
            for (Object[] objects : resultList) {
                Department department=(Department)objects[2];
                System.out.println(department.getId()+"\t"+department.getName());
                long count=(Long)objects[0];
                System.out.println("Count:"+count);
                Double salarySum=(Double)objects[1];
                System.out.println("Total Salary:"+salarySum);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}