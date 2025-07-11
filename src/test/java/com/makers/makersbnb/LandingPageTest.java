package com.makers.makersbnb;
import com.microsoft.playwright.*;
import org.junit.jupiter.api.*;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class LandingPageTest {
    static Playwright playwright;
    static Browser browser;
    BrowserContext context;
    Page page;

    @BeforeAll
    static void launchBrowser() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch();
    }

    @AfterAll
    static void closeBrowser() {
        playwright.close();
    }

    @BeforeEach
    void createContextAndPage() {
        context = browser.newContext();
        page = context.newPage();
    }

    @AfterEach
    void closeContext() {
        context.close();
    }

    private void performAuth0Login(String email, String password) {
        page.waitForURL("**/u/login**", new Page.WaitForURLOptions().setTimeout(5000));
        page.locator("input[name='username']").fill(email);
        page.locator("input[name='password']").fill(password);
        page.locator("button[type='submit'][value='default']").click();
        page.waitForURL("http://localhost:8080/**", new Page.WaitForURLOptions().setTimeout(10000));
    }

    @Test
    public void usersAreWelcomedToTheApp() {
        page.navigate("http://localhost:8080");
//        performAuth0Login("jonnyburdis@hotmail.com", "Passw0rd!");

        Locator pageBody = page.locator("body");
        assertThat(pageBody).containsText("Welcome to MakersBnB");
    }

    @Test
    public void displayAvailableSpaces() {
        page.navigate("http://localhost:8080");
//        performAuth0Login("jonnyburdis@hotmail.com", "Passw0rd!");

        Locator spacesId = page.locator("#spaces");
        assertThat(spacesId).containsText("3 spaces are waiting to be discovered");
    }

    @Test
    public void redirectToSpacesPage() {
        page.navigate("http://localhost:8080");
//        performAuth0Login("jonnyburdis@hotmail.com", "Passw0rd!");

        page.getByText("All spaces").click();
        Locator pageH1 = page.locator("h1");
        assertThat(pageH1).containsText("Our spaces");
    }

    @Test
    public void displayPreviousBookings() {
        page.navigate("http://localhost:8080");
//        performAuth0Login("jonnyburdis@hotmail.com", "Passw0rd!");

        Locator bookingId = page.locator("#past-bookings");
        assertThat(bookingId).containsText("123 bookings were made last week");
    }

    @Test
    public void contactEmailOnContactUsPage() {
        page.navigate("http://localhost:8080/contactus");
//        performAuth0Login("jonnyburdis@hotmail.com", "Passw0rd!");

        Locator pageBody = page.locator("body");
        assertThat(pageBody).containsText("email: makers@bnb.com");
    }

    @Test
    public void staffListedOnTeamPage() {
        page.navigate("http://localhost:8080/team");
//        performAuth0Login("jonnyburdis@hotmail.com", "Passw0rd!");

        Locator pageH2 = page.locator("h2");
        Locator pageH3 = page.locator("h3");
        Locator pageP = page.locator("p");
        assertThat(pageH2).containsText("Our team");
        assertThat(pageH3).containsText("Jon");
        assertThat(pageP).containsText("Role: Owner");
    }

    @Test
    public void usersSeeTsandCs() {
        page.navigate("http://localhost:8080");
//        performAuth0Login("jonnyburdis@hotmail.com", "Passw0rd!");

        page.getByText("Terms and Conditions").click();
        Locator pageTerms = page.locator(".terms-conditions");
        assertThat(pageTerms).containsText("Coming soon! In the meantime, please behave yourselves.");
    }
}
