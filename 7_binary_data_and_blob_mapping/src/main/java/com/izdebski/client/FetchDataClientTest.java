package com.izdebski.client;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Blob;
import java.util.List;

import org.hibernate.Session;

import com.izdebski.entities.Book;
import com.izdebski.entities.Person;
import com.izdebski.util.HibernateUtil;

public class FetchDataClientTest {

    public static void main(String[] args) {

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            long personId =  1L;
            Person person = session.get(Person.class, personId);
            System.out.println("Person details:");
            System.out.println(person.getId()+"\t"+person.getName());
            System.out.println("List of book:");
            List<Book> books = person.getBooks();
            for (Book book : books) {
                System.out.println(book.getId()+"\t"+book.getTitle()+"\t"+book.getIsbn());
            }

            Blob image = person.getProfilePic();
            InputStream inputStream = image.getBinaryStream();
            Files.copy(inputStream, Paths.get("outputProfilePics/"+person.getName()+".JPG"),StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}