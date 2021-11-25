package com.izdebski.client;

import com.izdebski.entities.Person;
import com.izdebski.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class HibernateBatchInsertClientTest {

    public static void main(String[] args) {
        Transaction tx =  null;
        int batchSize = 25;
        try(Session session = HibernateUtil.getSessionFactory().openSession() ) {
            tx = session.beginTransaction();
            //	for ( int i = 1; i <= 100_000; i++ ) {
            for ( int i = 1; i <= 100; i++ ) {//Keeping number of records less for demo purpose
                Person person = new Person();
                person.setFirstName("Gavin_"+i);
                person.setLastName("King_"+i);
                session.persist(person);
                if ( i > 0 && i % batchSize == 0 ) {
                    System.out.println("flush and clear the session");
                    //flush a batch of inserts and release memory
                    session.flush();
                    session.clear();
                }
            }
            tx.commit();
        } catch (Exception e) {
            if(tx != null && tx.isActive())
                tx.rollback();
            throw e;
        }
    }
}