package com.izdebski.client;

import java.util.List;

import com.izdebski.entities.Department;
import com.izdebski.entities.Employee;
import com.izdebski.entities.Project;
import com.izdebski.util.HibernateUtil;
import org.hibernate.Session;

public class DynamicHQLFetchingTest {

    public static void main(String[] args) {

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // Dynamic HQL fetching example
            String username = "barryj";
            String password = "barry@123";
            Employee employee = session
                    .createQuery("select e from Employee e left join fetch e.projects where "
                            + "e.username = :username and " + "e.password = :password", Employee.class)
                    .setParameter("username", username).setParameter("password", password).getSingleResult();

            if (employee != null) {
                System.out.println("Employee details::::::");
                System.out.println(employee.getId() + "\t" + employee.getEmployeeName() + "\t" + employee.getUsername()
                        + "\t" + employee.getPassword() + "\t" + employee.getAccessLevel());

                System.out.println("Employee projects details::::::");
                List<Project> projects = employee.getProjects();
                for (Project project : projects) {
                    System.out.println(project.getId()+"\t"+project.getProjectName());
                }
                System.out.println("Employee's department details::::::");
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