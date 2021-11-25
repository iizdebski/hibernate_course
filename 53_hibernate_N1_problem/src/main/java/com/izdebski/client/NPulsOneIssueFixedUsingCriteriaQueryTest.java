package com.izdebski.client;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;

import com.izdebski.entities.Department;
import com.izdebski.entities.Employee;
import com.izdebski.util.HibernateUtil;
import org.hibernate.Session;

public class NPulsOneIssueFixedUsingCriteriaQueryTest {

    public static void main(String[] args) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Department> query = builder.createQuery(Department.class);
            Root<Department> root = query.from(Department.class);
            root.fetch("employees", JoinType.LEFT);
            query.select(root);

            List<Department> departments = session.createQuery(query).getResultList();

            for (Department department : departments) {
                System.out.println("Department details:::::");

                System.out.println(department.getId()+"\t"+department.getDeptName());
                List<Employee> employees = department.getEmployees();
                System.out.println("Employees details::::::");
                for (Employee employee : employees) {

                    System.out.println(employee.getId() + "\t" + employee.getEmployeeName() + "\t" + employee.getUsername()
                            + "\t" + employee.getPassword() + "\t" + employee.getAccessLevel());
                }

            }
        }
    }
}