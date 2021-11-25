package com.izdebski.client;

import java.sql.CallableStatement;
import java.sql.Types;
import java.util.concurrent.atomic.AtomicReference;

import com.izdebski.util.HibernateUtil;
import org.hibernate.Session;

public class CallSqlFunctionInHibernateClientTest {

    public static void main(String[] args) {
//Because the currently StoredProcedureQuery implementation doesn’t yet support SQL
//functions, we need to use the JDBC syntax to call SQL Function as per hibernate Documentation.
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            int personId = 2;
            final AtomicReference<Integer> phoneCount = new AtomicReference<>();
            session.doWork( connection -> {
                try (CallableStatement callableStatement = connection.prepareCall(
                        "{ ? = call fn_count_phones(?) }" )) {
                    callableStatement.registerOutParameter( 1, Types.INTEGER );
                    callableStatement.setInt( 2, personId);
                    callableStatement.execute();
                    phoneCount.set(callableStatement.getInt(1) );
                }
            } );
            if(phoneCount != null){
                System.out.println("Phone Count:"+phoneCount.get());
            }

        }
    }
}