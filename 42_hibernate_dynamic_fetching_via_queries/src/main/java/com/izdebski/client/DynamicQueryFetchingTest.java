package com.izdebski.client;


import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;

import com.izdebski.entities.Department;
import com.izdebski.entities.Employee;
import com.izdebski.entities.Project;
import com.izdebski.util.HibernateUtil;
import org.hibernate.Session;

public class DynamicQueryFetchingTest {

    public static void main(String[] args) {

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            //Dynamic query fetching example
            String username = "barryj";
            String password = "barry@123";

            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Employee> query = builder.createQuery(Employee.class );
            Root<Employee> root = query.from(Employee.class );
            root.fetch( "projects", JoinType.LEFT);
            query.select(root).where(
                    builder.and(
                            builder.equal(root.get("username"), username),
                            builder.equal(root.get("password"), password)
                    )
            );
            Employee employee = session.createQuery( query ).getSingleResult();
            if (employee != null) {
                System.out.println("Employee details::::::");
                System.out.println(employee.getId() + "\t" + employee.getEmployeeName() + "\t" + employee.getUsername()
                        + "\t" + employee.getPassword() + "\t" + employee.getAccessLevel());
                System.out.println("Employee projects details::::::");
                List<Project> projects = employee.getProjects();
                for (Project project : projects) {
                    System.out.println(project.getId()+"\t"+project.getProjectName());
                }
                System.out.println("Employee's department details:");
                Department department = employee.getDepartment();
                if (department != null)
                    System.out.println(department.getId() + "\t" + department.getDeptName());
                else
                    System.out.println("Department details not found");

            } else {
                System.out.println("Employee not found with  provided credential");
            }
        }
    }
}