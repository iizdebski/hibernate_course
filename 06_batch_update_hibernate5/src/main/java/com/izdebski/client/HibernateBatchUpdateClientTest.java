package com.izdebski.client;

import com.izdebski.entities.Person;
import com.izdebski.util.HibernateUtil;
import org.hibernate.CacheMode;
import org.hibernate.ScrollMode;
import org.hibernate.ScrollableResults;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class HibernateBatchUpdateClientTest {

    public static void main(String[] args) {
        Session session = null;
        Transaction txn = null;
        ScrollableResults scrollableResults = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();

            txn = session.getTransaction();
            txn.begin();

            int batchSize = 5;
            scrollableResults = session.createQuery("select p from Person p").setCacheMode(CacheMode.IGNORE)
                    .scroll(ScrollMode.FORWARD_ONLY);

            int count = 0;
            while (scrollableResults.next()) {
                Person person = (Person) scrollableResults.get(0);
                processPerson(person);
                if (++count % batchSize == 0) {
                    // flush a batch of updates and release memory:
                    System.out.println("Session is Flushed...");
                    session.flush();
                    session.clear();
                }
            }

            txn.commit();
        } catch (RuntimeException e) {
            if (txn != null && txn.isActive())
                txn.rollback();
            throw e;
        } finally {
            if (scrollableResults != null) {
                scrollableResults.close();
            }
            if (session != null) {
                session.close();
            }
        }
    }

    private static void processPerson(Person person) {
        person.setFirstName(person.getFirstName() + "_updated");
    }
}