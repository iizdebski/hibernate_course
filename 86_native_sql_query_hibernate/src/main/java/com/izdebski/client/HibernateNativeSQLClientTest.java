package com.izdebski.client;

import java.math.BigInteger;
import java.util.List;

import com.izdebski.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;

public class HibernateNativeSQLClientTest {

    public static void main(String[] args) {
        //getPersonInfoV1();
        //getPersonInfoV2();
        getPersonInfoV3();
    }

    private static void getPersonInfoV3() {

        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            List<Object[]> list = session.createNativeQuery("SELECT id,name FROM Person").addScalar("id", LongType.INSTANCE).addScalar("name").list();
            for (Object[] objects : list) {
                Long id  =(Long)objects[0];
                String name=(String)objects[1];

                System.out.println("Person Id:"+id);
                System.out.println("Person Name:"+name);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void getPersonInfoV2() {

        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            List<Object[]> list = session.createNativeQuery("SELECT id,name FROM Person").addScalar("id", LongType.INSTANCE).addScalar("name", StringType.INSTANCE).list();
            for (Object[] objects : list) {
                Long id  =(Long)objects[0];
                String name=(String)objects[1];

                System.out.println("Person Id:"+id);
                System.out.println("Person Name:"+name);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    private static void getPersonInfoV1() {
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            List<Object[]> list = session.createNativeQuery("SELECT *FROM Person").list();
            for (Object[] objects : list) {
                BigInteger id  =(BigInteger)objects[0];
                String name=(String)objects[3];

                System.out.println("Person Id:"+id);
                System.out.println("Person Name:"+name);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}