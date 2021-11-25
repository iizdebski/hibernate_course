package com.izdebski.client;

import com.izdebski.entities.Book;
import com.izdebski.entities.Isbn;
import com.izdebski.util.HibernateUtil;
import org.hibernate.Session;


public class ReadByNaturalIdTest {

    public static void main(String[] args) {

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            System.out.println("Read entity by naturalId...");
            Book book2 = session.byNaturalId(Book.class).using("isbn", new Isbn("933-9730228524", "944-9730228524")).load();
            System.out.println(book2.getTitle());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}