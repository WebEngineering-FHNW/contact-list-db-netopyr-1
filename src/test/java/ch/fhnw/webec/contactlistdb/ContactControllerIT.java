package ch.fhnw.webec.contactlistdb;

import ch.fhnw.webec.contactlistdb.model.Contact;
import ch.fhnw.webec.contactlistdb.model.Phone;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Transactional
@AutoConfigureMockMvc
public class ContactControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private EntityManager entityManager;

    @Test
    void findByIdShouldReturnCorrectData() throws Exception {
        // given
        final Contact contact = new Contact();
        contact.setFirstName("Jon");
        contact.setLastName("Snow");
        contact.getEmails().add("jon.snow@thewall.com");
        contact.getEmails().add("jon@snow.com");
        contact.setJobTitle("Ranger");
        contact.setCompany("Night's watch");
        contact.getPhones().add(new Phone("+42", "123", "456789"));
        contact.getPhones().add(new Phone("+42", "987", "654321"));
        entityManager.persist(contact);
        entityManager.flush();

        // when
        final ResultActions result = mockMvc.perform(MockMvcRequestBuilders.get("/?select= " + contact.getId()));

        // then
        result.andExpect(status().isOk())
                .andExpect(content().string(containsString("Jon")))
                .andExpect(content().string(containsString("Snow")))
                .andExpect(content().string(containsString("jon.snow@thewall.com")))
                .andExpect(content().string(containsString("jon@snow.com")))
                .andExpect(content().string(containsString("Ranger")))
                .andExpect(content().string(containsString("Night&#39;s watch")))
                .andExpect(content().string(containsString("(+42) 123-456789")))
                .andExpect(content().string(containsString("(+42) 987-654321")));
    }

}
