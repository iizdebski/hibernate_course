package com.izdebski.client;

import org.hibernate.Session;

import com.izdebski.entities.Person;
import com.izdebski.util.HibernateUtil;

public class ReadEntityClientTest {

    public static void main(String[] args) {

        //getPersonById();
        loadPersonById();
    }

    private static void loadPersonById() {
        try(Session session = HibernateUtil.getSessionFactory().openSession() ) {

            long personId = 10L;

            Person person = session.load(Person.class, personId);
            if(person != null){
                System.out.println(person.getId()+"\t"+person.getName());
            }else{
                System.out.println("Person Doesn't Exist..");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private static void getPersonById() {
        try(Session session = HibernateUtil.getSessionFactory().openSession() ) {

            long personId = 10L;

            Person person = session.get(Person.class, personId);
            if(person != null){
                System.out.println(person.getId()+"\t"+person.getName());
            }else{
                System.out.println("Person Doesn't Exist..");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}