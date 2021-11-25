package com.izdebski.client;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Root;

import org.hibernate.Session;

import com.izdebski.dto.EmployeeStatisticsInfo;
import com.izdebski.entities.Employee;
import com.izdebski.util.HibernateUtil;

public class CriteriaQueryAggregateFunctionsClientTest {

    public static void main(String[] args) {


        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            CriteriaBuilder builder = session.getCriteriaBuilder();

            CriteriaQuery<EmployeeStatisticsInfo> criteriaQuery = builder.createQuery(EmployeeStatisticsInfo.class);
            Root<Employee> root = criteriaQuery.from(Employee.class);


            Expression<Long> totalNoOfEmployees = builder.count(root);
            Expression<Long> totalDistnctNoOfEmployees = builder.countDistinct(root);
            Expression<Double> maxSalaryOfEmployee = builder.max(root.get("salary"));
            Expression<Double> avgSalaryOfEmployees = builder.avg(root.get("salary"));
            Expression<Double> sumOfSalryOfEmployees = builder.sum(root.get("salary"));
            criteriaQuery.select(builder.construct(EmployeeStatisticsInfo.class, totalNoOfEmployees,totalDistnctNoOfEmployees,maxSalaryOfEmployee,avgSalaryOfEmployees,sumOfSalryOfEmployees));

            EmployeeStatisticsInfo singleResult = session.createQuery(criteriaQuery).getSingleResult();
            System.out.println(singleResult);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}