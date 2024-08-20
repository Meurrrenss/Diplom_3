package site.nomoreparties.stellarburgers.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Header {
    private final WebDriver driver;

    //Локатор кнопки "Конструктор" в хедере
    protected final By constructorButton = By.xpath(".//p[text() = 'Конструктор']");

    // Локатор логотипа "Stellar Burgers" в хедере
    protected final By logo = By.xpath(".//div[@class = 'AppHeader_header__logo__2D0X2']");

    //Локатор кнопки "Личный кабинет" в хедере
    protected final By personalAccountButton = By.xpath(".//p[text()='Личный Кабинет']");

    public Header(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Кликн на кнопку 'Конструктор' в хедере")
    public MainPage clickConstructorButton() {
        driver.findElement(constructorButton).click();
        return new MainPage(driver);
    }

    @Step("Клик на логотип сервиса в хедере")
    public MainPage clickLogo() {
        driver.findElement(logo).click();
        return new MainPage(driver);
    }

    @Step("Клик на кнопку 'Личный кабинет' в хедере авторизованным пользователем")
    public ProfilePage clickPersonalAccountButtonWithAuthorization() {
        driver.findElement(personalAccountButton).click();
        return new ProfilePage(driver);
    }

    @Step("Клик на кнопку 'Личный кабинет' в хедере не авторизованным пользователем")
    public AuthorizationPage clickPersonalAccountButtonWithoutAuthorization() {
        driver.findElement(personalAccountButton).click();
        return new AuthorizationPage(driver);
    }
}
