package com.izdebski.client;

import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;

import com.izdebski.util.HibernateUtil;
import org.hibernate.Session;

public class CallStoredProcedureInHibernateClientTest {

    public static void main(String[] args) {

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            StoredProcedureQuery procedureQuery = session.createStoredProcedureQuery("sp_count_phones");
            procedureQuery.registerStoredProcedureParameter( "personId", Long.class, ParameterMode.IN);
            procedureQuery.registerStoredProcedureParameter( "phoneCount", Long.class, ParameterMode.OUT);

            procedureQuery.setParameter("personId", 5L);

            procedureQuery.execute();
            Long phoneCount = (Long) procedureQuery.getOutputParameterValue("phoneCount");

            System.out.println("Phone Count:"+phoneCount);
        }
    }
}