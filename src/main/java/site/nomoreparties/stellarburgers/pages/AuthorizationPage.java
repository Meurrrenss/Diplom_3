package site.nomoreparties.stellarburgers.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AuthorizationPage extends Header {

    private final WebDriver driver;

    // Локатор текста "Вход" на стринице авторизации
    private final By loginTitle = By.xpath(".//h2[text() = 'Вход']");

    // Локатор поля Email на стринице авторизации
    private final By emailField = By.xpath(".//label[text() = 'Email']/../input[@class='text input__textfield text_type_main-default']");

    // Локатор поля "Пароль" на странице авторизации
    private final By passwordField = By.xpath(".//label[text() = 'Пароль']/../input[@class='text input__textfield text_type_main-default']");

    // Локатор кнопки "Войти" на странице авторизации
    private final By loginButton = By.xpath(".//button[text() = 'Войти']");

    // Локатор кнопки "Зарегистрироваться" на странице авторизации
    private final By registerLink = By.xpath(".//a[text() = 'Зарегистрироваться']");

    // Локатор кнопки "Восстановить пароль" на странице авторизации
    private final By recoveryPasswordLink = By.xpath(".//a[text() = 'Восстановить пароль']");


    public AuthorizationPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    // Ожидание загрузки страницы авторизации
    public AuthorizationPage waitLoadPageAuthorizationForm() {
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.visibilityOfElementLocated(loginTitle));
        return this;
    }

    @Step("Отображение страницы авторизации")
    public boolean titleLoginIsDisplayed() {
        return driver.findElement(loginTitle).isDisplayed();
    }

    @Step("Заполнить поле Email в форме авторизации")
    public AuthorizationPage fillInTheEmailField(String email) {
        driver.findElement(emailField).sendKeys(email);
        return this;
    }

    @Step("Заполнить поле Пароль в форме авторизации")
    public AuthorizationPage fillInThePasswordField(String password) {
        driver.findElement(passwordField).sendKeys(password);
        return this;
    }

    @Step("Клик на кнопку 'Войти'")
    public MainPage clickEnterButton() {
        driver.findElement(loginButton).click();
        return new MainPage(driver);
    }

    @Step("Клик на кнопку 'Зарегистрироваться'")
    public RegistrationPage clickRegisterLink() {
        driver.findElement(registerLink).click();
        return new RegistrationPage(driver);
    }

    @Step("Кликнуть на кнопку 'Восстановить пароль'")
    public PasswordRecoveryPage clickPasswordRecoveryLink() {
        driver.findElement(recoveryPasswordLink).click();
        return new PasswordRecoveryPage(driver);
    }
}
