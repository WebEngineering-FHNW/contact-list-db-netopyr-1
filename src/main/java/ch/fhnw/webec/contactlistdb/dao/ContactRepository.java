package ch.fhnw.webec.contactlistdb.dao;

import ch.fhnw.webec.contactlistdb.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ContactRepository extends JpaRepository<Contact, Long> {

    Optional<Contact> findByLastNameContainingOrFirstNameContainingAllIgnoreCase(String lastName, String firstName);

}
