package com.izdebski.client;

import java.util.List;

import com.izdebski.entities.Employee;
import com.izdebski.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

public class FetchDataClientTest {

    public static void main(String[] args) {
        //getEmployeeAndAdressByEmployeeId1();
        getEmployeeAndAdressByEmployeeId2();
    }

    private static void getEmployeeAndAdressByEmployeeId2() {
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {

            String HQL ="SELECT emp.employeeName,emp.salary,addr.city,addr.pincode FROM Employee emp LEFT JOIN emp.addressList addr "
                    + " WHERE emp.employeeId=:empId";
            Query<Object[]> query = session.createQuery(HQL);
            query.setParameter("empId", 1);
            List<Object[]> list = query.list();
            for (Object[] objects : list) {
                String employeeName=(String)objects[0];
                Double sal=(Double)objects[1];
                String city=(String)objects[2];
                Long pincode=(Long)objects[3];
                System.out.println(employeeName+"\t"+sal+"\t"+city+"\t"+pincode);
            }
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }
    private static void getEmployeeAndAdressByEmployeeId1() {
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {

            String HQL ="FROM Employee WHERE employeeId=:empId";
            Query<Employee> query = session.createQuery(HQL, Employee.class);
            query.setParameter("empId", 1);
            Employee employee = query.uniqueResult();
            System.out.println(employee);
            if(employee != null){
                employee.getAddressList().forEach(System.out::println);
            }
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }
}