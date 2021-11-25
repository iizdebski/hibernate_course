package com.izdebski.client;

import java.util.List;

import com.izdebski.entities.Customer;
import com.izdebski.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;

public class ReadingEntityAuditInfoTest {

    public static void main(String[] args) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            long customerId = 1L;

            AuditReader auditReader = AuditReaderFactory.get(session);
            List<Number> revisions = AuditReaderFactory.get(session).getRevisions(Customer.class, customerId);
            for (Number rev : revisions) {
                Customer customer = auditReader.find(Customer.class, customerId, rev);
                System.out.println("Revision No:"+rev);
                System.out.println(customer);
            }

        } catch (Exception e) {
            throw e;
        }
    }
}