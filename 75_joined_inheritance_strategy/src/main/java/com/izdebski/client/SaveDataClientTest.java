package com.izdebski.client;

import java.math.BigDecimal;
import java.text.ParseException;

import com.izdebski.entities.Employee;
import com.izdebski.entities.Person;
import com.izdebski.entities.Student;
import com.izdebski.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;

public class SaveDataClientTest {

    public static void main(String[] args) throws ParseException {
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            Person person = new Person();
            person.setName("Sveta Svitlenki");
            person.setGender("Female");

            Employee employee = new Employee();
            employee.setBonus(new BigDecimal("277.389"));
            employee.setDeptName("IT");
            employee.setDoj(HibernateUtil.getDoj("18/12/2015"));
            employee.setEmail("st.st@gmail.com");
            employee.setName("Stepanenko");
            employee.setSalary(80000.2872);
            employee.setGender("Male");

            Student student = new Student();
            student.setName("Abramenko");
            student.setGender("Female");
            student.setFee(20000.00f);
            student.setSchoolName("DPS");
            student.setSectionName("12th Std");

            session.beginTransaction();
            session.save(person);
            session.save(student);
            session.save(employee);

            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }
}