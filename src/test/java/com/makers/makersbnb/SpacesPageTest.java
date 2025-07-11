package com.makers.makersbnb;
import com.microsoft.playwright.*;
import org.junit.jupiter.api.*;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class SpacesPageTest {
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
    public void usersSeeListOfSpaces() {
        page.navigate("http://localhost:8080/spaces");
//        performAuth0Login("jonnyburdis@hotmail.com", "Passw0rd!");

        assertThat(page.locator(".space-name").nth(0)).containsText("Treehouse");
        assertThat(page.locator(".space-name").nth(1)).containsText("Attic");
        assertThat(page.locator(".space-name").nth(2)).containsText("Townhouse");

        assertThat(page.locator(".space-description").nth(0)).containsText("Small place with a natural feel");
        assertThat(page.locator(".space-description").nth(1)).containsText("Bright loft with large skylights");
        assertThat(page.locator(".space-description").nth(2)).containsText("Luxury space in the middle of the city");
    }
}
