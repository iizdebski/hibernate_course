package com.izdebski.client;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.izdebski.entities.Employee;
import com.izdebski.util.HibernateUtil;

public class CriteriaQueryOrderByClientTest {

    public static void main(String[] args) {

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            CriteriaBuilder builder = session.getCriteriaBuilder();

            CriteriaQuery<Employee> criteriaQuery = builder.createQuery(Employee.class);
            Root<Employee> root = criteriaQuery.from(Employee.class);
            criteriaQuery.select(root);
            //  criteriaQuery.orderBy(builder.asc(root.get("salary")));
            criteriaQuery.orderBy(builder.desc(root.get("salary")));
            Query<Employee> query = session.createQuery(criteriaQuery);
            List<Employee> list = query.getResultList();
            for (Employee employee : list) {
                System.out.println("Employee Name:"+employee.getName()+"\t"+"Salary:"+employee.getSalary());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}