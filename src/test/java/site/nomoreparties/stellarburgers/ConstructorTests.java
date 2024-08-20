package site.nomoreparties.stellarburgers;

import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import site.nomoreparties.stellarburgers.drivers.WebDriverConstructor;
import site.nomoreparties.stellarburgers.pages.MainPage;

import static org.junit.Assert.assertTrue;

@DisplayName("Раздел 'Конструктор'")
public class ConstructorTests {
    private WebDriver driver;
    private MainPage homePage;
    private boolean sectionIsDisplayed;


    @Before
    public void setUp() {
        WebDriverConstructor webDriverConstructor = new WebDriverConstructor();
        driver = webDriverConstructor.getWebDriver();
        homePage = new MainPage(driver)
                .open()
                .waitLoadHomePage();
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Test
    @DisplayName("Переход к разделу 'Булки' кликом на таб")
    public void goToBunSectionTest() {
        sectionIsDisplayed = homePage
                .clickSauceSection()
                .clickBunSection()
                .bunsSectionIsActive();
        assertTrue(sectionIsDisplayed); // Проверка, что секция булок в активном/выбранном состоянии
    }

    @Test
    @DisplayName("Переход к разделу 'Соусы' кликом на таб")
    public void goToSauceSectionTest() {
        sectionIsDisplayed = homePage
                .clickSauceSection()
                .saucesSectionIsActive();
        assertTrue(sectionIsDisplayed); // Проверка, что секция соусов в активном/выбранном состояни
    }

    @Test
    @DisplayName("Переход к разделу 'Начинки' кликом на таб")
    public void goToFilingSectionTest() {
        sectionIsDisplayed = homePage
                .clickFilingSection()
                .filingsSectionIsActive();
        assertTrue(sectionIsDisplayed); // Проверка, что секция начинок в активном/выбранном состояни
    }

}
