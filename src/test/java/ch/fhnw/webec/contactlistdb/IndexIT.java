package ch.fhnw.webec.contactlistdb;

import ch.fhnw.webec.contactlistdb.pages.IndexPage;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class IndexIT {

    @LocalServerPort
    private int port;

    private final WebDriver driver = new HtmlUnitDriver();

    @Test
    void initialPageShouldNotShowPersonDetails() {
        // when
        final IndexPage page = IndexPage.to(driver, port);

        // then
        assertThat(page.getContactDetails()).isEmpty();
        assertThat(page.getNoContactMessage()).isNotEmpty();
    }

    @Test
    void clickingContactShouldShowContactDetails() {
        // given
        final IndexPage page = IndexPage.to(driver, port);

        // when
        page.getContactLinks().get(5).click();

        // then
        assertThat(page.getContactDetails()).isNotEmpty();
        assertThat(page.getNoContactMessage()).isEmpty();
    }

    @Test
    void clickingContactShouldShowCorrectContactDetails() {
        // given
        final IndexPage page = IndexPage.to(driver, port);

        // when
        page.getContactLinks().get(3).click();

        // then
        assertThat(page.getSelectedFirstName()).hasValue("Bax");
        assertThat(page.getSelectedLastame()).hasValue("McGrath");
        assertThat(page.getSelectedEmails()).hasValueSatisfying(emails -> {
            assertThat(emails).contains("dpocock0@google.es");
            assertThat(emails).contains("hdafforne1@slashdot.org");
        });
    }

    @Test
    void clickingTwoContactsShouldShowCorrectContactDetails() {
        // given
        final IndexPage page = IndexPage.to(driver, port);
        page.getContactLinks().get(3).click();

        // when
        page.getContactLinks().get(11).click();

        // then
        assertThat(page.getSelectedFirstName()).hasValue("Marian");
        assertThat(page.getSelectedLastame()).hasValue("Blacket");
        assertThat(page.getSelectedEmails()).hasValueSatisfying(emails -> {
            assertThat(emails).contains("bgosker0@hugedomains.com");
        });
    }
}
