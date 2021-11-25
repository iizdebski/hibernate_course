package com.izdebski.client;

import org.hibernate.Session;

import com.izdebski.entities.Person;
import com.izdebski.util.HibernateUtil;

public class ClientTest {

    public static void main(String[] args) {

        try(Session session = HibernateUtil.getSessionFactory().openSession() ) {
            Person person1 = new Person();
            person1.setName("Stephen King");
            person1.setEmail("gavin.k@buggy.com");

            Person person2 = new Person();
            person2.setClientId(1001L);
            person2.setEmail("st.ki@king.com");

            session.beginTransaction();

            session.save(person1);
            session.save(person2);

            session.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}