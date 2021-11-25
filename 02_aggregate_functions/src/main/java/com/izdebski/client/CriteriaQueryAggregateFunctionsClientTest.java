package com.izdebski.client;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;

import com.izdebski.entities.Employee;
import com.izdebski.util.HibernateUtil;

public class CriteriaQueryAggregateFunctionsClientTest {

    public static void main(String[] args) {
        //getTotalNoOfEmployees();
        //getMaxSalaryOfEmployee();
        //getAvgSalaryOfEmployees();
        getSumOfSalryOfEmployees();
        //getTotalDistnctNoOfEmployees();
    }

    //Count Distinct Employee
    private static void getTotalDistnctNoOfEmployees() {

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            CriteriaBuilder builder = session.getCriteriaBuilder();

            CriteriaQuery<Long> criteriaQuery = builder.createQuery(Long.class);
            Root<Employee> root = criteriaQuery.from(Employee.class);
            criteriaQuery.select(builder.countDistinct(root));

            Long result = session.createQuery(criteriaQuery).getSingleResult();
            System.out.println("Total Distinct No Of Employees:"+result);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    //Calucate Sum of Employees Salary
    private static void getSumOfSalryOfEmployees() {

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            CriteriaBuilder builder = session.getCriteriaBuilder();

            CriteriaQuery<Double> criteriaQuery = builder.createQuery(Double.class);
            Root<Employee> root = criteriaQuery.from(Employee.class);
            criteriaQuery.select(builder.sum(root.get("salary")));

            Double result = session.createQuery(criteriaQuery).getSingleResult();
            System.out.println("Sum Of Salary:"+result);

        } catch (Exception e) {
            e.printStackTrace();
        }


    }
    //Calculate Average Salary of Employees
    private static void getAvgSalaryOfEmployees() {

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            CriteriaBuilder builder = session.getCriteriaBuilder();

            CriteriaQuery<Double> criteriaQuery = builder.createQuery(Double.class);
            Root<Employee> root = criteriaQuery.from(Employee.class);
            criteriaQuery.select(builder.avg(root.get("salary")));

            Double result = session.createQuery(criteriaQuery).getSingleResult();
            System.out.println("Avg Salary:"+result);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    //Get Max Salary of Employee
    private static void getMaxSalaryOfEmployee() {

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            CriteriaBuilder builder = session.getCriteriaBuilder();

            CriteriaQuery<Double> criteriaQuery = builder.createQuery(Double.class);
            Root<Employee> root = criteriaQuery.from(Employee.class);
            criteriaQuery.select(builder.max(root.get("salary")));

            Double result = session.createQuery(criteriaQuery).getSingleResult();
            System.out.println("Max Salary:"+result);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Count of Employees
    private static void getTotalNoOfEmployees() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            CriteriaBuilder builder = session.getCriteriaBuilder();

            CriteriaQuery<Long> criteriaQuery = builder.createQuery(Long.class);
            Root<Employee> root = criteriaQuery.from(Employee.class);
            criteriaQuery.select(builder.count(root));

            Long result = session.createQuery(criteriaQuery).getSingleResult();
            System.out.println("Total No Of Employees:"+result);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}