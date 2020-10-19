package ch.fhnw.webec.contactlistdb;

import ch.fhnw.webec.contactlistdb.dao.ContactRepository;
import ch.fhnw.webec.contactlistdb.model.Contact;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.persistence.EntityManager;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class ContactRepositoryIT {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private ContactRepository repository;

    @Test
    void findByIdShouldReturnCorrectData() {
        // given
        final Contact contact = new Contact();
        contact.setFirstName("Jon");
        contact.setLastName("Snow");
        contact.getEmails().add("jon.snow@thewall.com");
        contact.getEmails().add("jon@snow.com");
        entityManager.persist(contact);
        entityManager.flush();

        // when
        final Optional<Contact> result = repository.findById(contact.getId());

        // then
        assertThat(result).hasValue(contact);
    }

}
