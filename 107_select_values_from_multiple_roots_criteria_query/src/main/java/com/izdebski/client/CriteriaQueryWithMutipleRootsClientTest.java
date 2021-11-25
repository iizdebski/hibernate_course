package com.izdebski.client;

import java.util.List;

import javax.persistence.Tuple;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Session;

import com.izdebski.entities.Partner;
import com.izdebski.entities.Person;
import com.izdebski.entities.Phone;
import com.izdebski.util.HibernateUtil;

public class CriteriaQueryWithMutipleRootsClientTest {

    public static void main(String[] args) {

        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();

            CriteriaQuery<Tuple> criteria = builder.createQuery( Tuple.class );

            Root<Person> personRoot = criteria.from(Person.class);
            Root<Partner> partnerRoot = criteria.from(Partner.class);
            criteria.multiselect( personRoot, partnerRoot );

            Predicate personRestriction = builder.and(
                    builder.equal(personRoot.get("address"), "Bank of Canada,234 Wellington Street")
            );
            Predicate partnerRestriction = builder.and(
                    builder.like(partnerRoot.<String>get("name" ), "%Mur%" ),
                    builder.equal( partnerRoot.get("version"), 1)
            );
            criteria.where(builder.and( personRestriction, partnerRestriction ) );

            List<Tuple> tuples = session.createQuery( criteria ).getResultList();
            for (Tuple tuple : tuples) {
                Person person = (Person)tuple.get(0);
                if(person != null){
                    System.out.println(person);
                    List<Phone> phones = person.getPhones();
                    for (Phone phone : phones) {
                        System.out.println(phone.getId()+"\t"+phone.getNumber()+"\t"+phone.getType().toString());
                    }
                }

                Partner partner = (Partner)tuple.get(1);
                System.out.println(partner);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}