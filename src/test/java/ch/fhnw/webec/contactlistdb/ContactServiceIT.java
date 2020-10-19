package ch.fhnw.webec.contactlistdb;

import ch.fhnw.webec.contactlistdb.model.Contact;
import ch.fhnw.webec.contactlistdb.service.ContactService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
public class ContactServiceIT {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private ContactService service;

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
        final Optional<Contact> result = service.findContact(contact.getId());

        // then
        assertThat(result).hasValue(contact);
    }


}
