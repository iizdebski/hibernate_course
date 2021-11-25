package com.izdebski.client;

import com.izdebski.entities.Department;
import com.izdebski.entities.Employee;
import com.izdebski.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

public class DirectEntityFetchingAndEntityQueryTest {

    public static void main(String[] args) {
        //directEntityFetching();
        entityQueryFetching();
    }

    private static void entityQueryFetching() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            //Direct Entity fetching exmaple
            Long employeeId = 1L;
            Query<?> query = session.createQuery("SELECT e FROM Employee e WHERE e.id =:empId");
            query.setParameter("empId", employeeId);
            Object object = query.getSingleResult();
            Employee employee =(Employee)object;
            if(employee != null){
                System.out.println("Employee details::::::");
                System.out.println(employee.getId()+"\t"+employee.getEmployeeName()+"\t"+employee.getUsername()+"\t"+employee.getPassword()+"\t"+employee.getAccressLevel());
                System.out.println("Employee's department details:");
                Department department = employee.getDepartment();
                if(department != null)
                    System.out.println(department.getId()+"\t"+department.getDeptName());
                else
                    System.out.println("Department details not found for employee whose ID is:"+employeeId);

            }else{
                System.out.println("Employee not found with ID:"+employeeId);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static void directEntityFetching() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            //Direct Entity fetching exmaple
            Long employeeId = 1L;
            Employee employee = session.get(Employee.class, employeeId);
            if(employee != null){
                System.out.println("Employee details::::::");
                System.out.println(employee.getId()+"\t"+employee.getEmployeeName()+"\t"+employee.getUsername()+"\t"+employee.getPassword()+"\t"+employee.getAccressLevel());
                System.out.println("Employee's department details:");
                Department department = employee.getDepartment();
                if(department != null)
                    System.out.println(department.getId()+"\t"+department.getDeptName());
                else
                    System.out.println("Department details not found for employee whose ID is:"+employeeId);

            }else{
                System.out.println("Employee not found with ID:"+employeeId);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}