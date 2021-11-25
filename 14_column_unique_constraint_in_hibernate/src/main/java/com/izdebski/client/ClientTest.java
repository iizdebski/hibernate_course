package com.izdebski.client;

import org.hibernate.Session;

import com.izdebski.entities.Author;
import com.izdebski.entities.Book;
import com.izdebski.util.HibernateUtil;

public class ClientTest {


    public static void main(String[] args) {

        try(Session session = HibernateUtil.getSessionFactory().openSession() ) {

            Author  author = new Author();
            author.setFirstName("Stephen");
            author.setLastName("King");

            Book book1 = new Book();
            book1.setBookPrice(900.00);
            book1.setIsbn("900-987654312");
            book1.setTitle("Hibernate In Action");
            book1.setAuthor(author);

			/*Book book2 = new Book();
			book2.setBookPrice(600.00);
			book2.setIsbn("911-987654312");
			book2.setTitle("Hibernate In Action");
			book2.setAuthor(author);*/

            author.getBooks().add(book1);
            //author.getBooks().add(book2);

            session.beginTransaction();

            session.save(author);
            session.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}