package ch.fhnw.webec.contactlistdb.dao;

import ch.fhnw.webec.contactlistdb.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository extends JpaRepository<Contact, Long> {
}
