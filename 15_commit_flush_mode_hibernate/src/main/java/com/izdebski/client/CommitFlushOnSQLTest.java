package com.izdebski.client;

import java.math.BigInteger;

import org.hibernate.Session;

import com.izdebski.entities.Person;
import com.izdebski.util.HibernateUtil;

public class CommitFlushOnSQLTest {

    public static void main(String[] args) {

        try(Session session = HibernateUtil.getSessionFactory().openSession() ) {

            Person  person = new Person();
            person.setFirstName("Stephen");
            person.setLastName("King");

            session.beginTransaction();

            session.save(person);
            session.getTransaction().commit();
            BigInteger count=(BigInteger)	session .createNativeQuery("select count(*) from Person")
                    .getSingleResult();
            System.out.println(count);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}