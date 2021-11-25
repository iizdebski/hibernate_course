package com.izdebski.client;

import org.hibernate.Session;

import com.izdebski.entities.Account;
import com.izdebski.util.HibernateUtil;

public class FetchDataClientTest {

    public static void main(String[] args) {

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            long accountId =  1L;

            Account account = session.get(Account.class, accountId);

            System.out.println("Account ID:"+account.getId());
            System.out.println("Rate amout:"+account.getRate());
            System.out.println("Credit amount:"+account.getCredit());

            System.out.println("Interest amount:"+account.getInterest());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}