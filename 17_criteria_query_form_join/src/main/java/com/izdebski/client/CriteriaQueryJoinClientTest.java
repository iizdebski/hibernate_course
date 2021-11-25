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

public class CriteriaQueryJoinClientTest {

    public static void main(String[] args) {


        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            CriteriaBuilder builder = session.getCriteriaBuilder();

            CriteriaQuery<Object[]> criteriaQuery = builder.createQuery(Object[].class);
            Root<Employee> rootEmp = criteriaQuery.from(Employee.class);
            Root<Department> rootDept = criteriaQuery.from(Department.class);

            criteriaQuery.multiselect(rootEmp,rootDept);
            criteriaQuery.where(builder.equal(rootEmp.get("department"), rootDept.get("id")));

            Query<Object[]> query = session.createQuery(criteriaQuery);

            List<Object[]> resultList = query.getResultList();
            for (Object[] objects : resultList) {
                Employee employee=(Employee)objects[0];
                System.out.println(employee.getId()+"\t"+employee.getName());
                Department department=(Department)objects[1];

                System.out.println(department.getId()+"\t"+department.getName());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}