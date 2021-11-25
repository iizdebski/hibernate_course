package com.izdebski.client;

import org.hibernate.Session;

import com.izdebski.entities.Account;
import com.izdebski.util.HibernateUtil;

public class PersistEntityClientTest {

    public static void main(String[] args) {

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Account account = new Account();
            account.setCredit(9000.00);
            account.setRate(8.35);

            session.beginTransaction();
            session.save(account);
            session.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}