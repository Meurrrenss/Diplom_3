package site.nomoreparties.stellarburgers.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PasswordRecoveryPage extends Header {
    private final WebDriver driver;

    // Локатор кнопки "Войти" на странице восстановления пароля
    private final By loginButton = By.xpath(".//a[text() = 'Войти']");

    public PasswordRecoveryPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    @Step("Клик на кнопку <Войти>")
    public AuthorizationPage clickLoginLink() {
        driver.findElement(loginButton).click();
        return new AuthorizationPage(driver);
    }
}
