package com.izdebski.client;

import org.hibernate.Session;

import com.izdebski.entities.Person;
import com.izdebski.util.HibernateUtil;

public class EvictEntityFromSessionCacheClientTest {

    public static void main(String[] args) {

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            long personId = 1L;
            Person person = session.get(Person.class,personId);
            System.out.println(person.getName());

            session.evict(person);
            //session.clear();
            System.out.println("------------------------------");

            person = session.get(Person.class,personId);

            System.out.println(person.getName());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}