package com.izdebski.client;

import java.util.Collections;
import java.util.List;

import com.izdebski.entities.Department;
import com.izdebski.entities.Employee;
import com.izdebski.entities.Project;
import com.izdebski.util.HibernateUtil;
import org.hibernate.Session;

public class DynamicFetchingUsingJPASubGraphTest {

    public static void main(String[] args) {

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // Dynamic HQL fetching example
            Long projectId = 1L;
            Project project = session.find(Project.class, projectId, Collections
                    .singletonMap("javax.persistence.fetchgraph", session.getEntityGraph("project.employees")));

            System.out.println("Project details::");
            System.out.println(project.getId() + "\t" + project.getProjectName());
            System.out.println("Employees details:::");
            List<Employee> employees = project.getEmployees();
            for (Employee employee : employees) {
                System.out.println(employee.getId() + "\t" + employee.getEmployeeName() + "\t" + employee.getUsername()
                        + "\t" + employee.getPassword() + "\t" + employee.getAccessLevel());

                Department department = employee.getDepartment();
                if (department != null) {
                    System.out.println("Employee's department details::::::");
                    System.out.println(department.getId() + "\t" + department.getDeptName());
                }

            }
        }

    }
}