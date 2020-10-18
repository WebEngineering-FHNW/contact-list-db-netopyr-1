package ch.fhnw.webec.contactlistdb.service;

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

    private final ObjectMapper mapper;

    private List<Contact> contacts;

    @Autowired
    public ContactService(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    @PostConstruct
    public void init() throws IOException {
        contacts = mapper.readValue(ContactService.class.getResource(JSON_FILE), new TypeReference<List<Contact>>() {
        });
    }

    public List<Contact> getAllContacts() {
        return contacts;
    }

    public Optional<Contact> findContact(long id) {
        return contacts.stream().filter(contact -> contact.getId() == id).findAny();
    }
}
