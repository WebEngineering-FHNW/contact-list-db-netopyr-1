package ch.fhnw.webec.contactlistdb.service;

import ch.fhnw.webec.contactlistdb.dao.ContactRepository;
import ch.fhnw.webec.contactlistdb.model.Contact;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class ContactService {

    private static final String JSON_FILE = "contacts.json";

    private final ContactRepository contactRepository;
    private final ObjectMapper mapper;

    @Autowired
    public ContactService(ContactRepository contactRepository, ObjectMapper mapper) {
        this.contactRepository = contactRepository;
        this.mapper = mapper;
    }

    @PostConstruct
    public void init() throws IOException {
        mapper.readValue(ContactService.class.getResource(JSON_FILE), new TypeReference<List<Contact>>() {})
        .forEach(contact -> {
            contact.setId(0L);
            contactRepository.save(contact);
        });
    }

    public List<Contact> getAllContacts() {
        return contactRepository.findAll();
    }

    public Optional<Contact> findContact(long id) {
        return contactRepository.findById(id);
    }
}
