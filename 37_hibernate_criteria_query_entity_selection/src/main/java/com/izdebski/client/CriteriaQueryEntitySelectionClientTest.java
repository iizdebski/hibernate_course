package com.izdebski.client;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.izdebski.entities.Employee;
import com.izdebski.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

public class CriteriaQueryEntitySelectionClientTest {

    public static void main(String[] args) {

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Employee> criteriaQuery = builder.createQuery(Employee.class);
            Root<Employee> root = criteriaQuery.from(Employee.class);
            criteriaQuery.select(root);

            criteriaQuery.where(builder.equal(root.get("employeeId"), 2));

            Query<Employee> query = session.createQuery(criteriaQuery);
            List<Employee> empList = query.list();
            empList.forEach(System.out::println);
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }
}