package com.izdebski.client;

import javax.persistence.FlushModeType;

import org.hibernate.Session;

import com.izdebski.entities.Person;
import com.izdebski.util.HibernateUtil;

public class CommitFlushOnHQLTest {

    public static void main(String[] args) {

        try(Session session = HibernateUtil.getSessionFactory().openSession() ) {

            Person  person = new Person();
            person.setFirstName("Stephen");
            person.setLastName("King");

            session.beginTransaction();
            session.save(person);
            session.getTransaction().commit();
            session.createQuery("select p from Advertisement p")
                    .setFlushMode( FlushModeType.COMMIT)
                    .getResultList();

            session.createQuery("select p from Person p")
                    .setFlushMode( FlushModeType.COMMIT)
                    .getResultList();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}