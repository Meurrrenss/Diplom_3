package site.nomoreparties.stellarburgers.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegistrationPage extends Header {
    private final WebDriver driver;

    // Локатор текста "Регистрация" на странице регистрации
    private final By titleRegistration = By.xpath(".//h2[text()='Регистрация']");

    // Локатор поля ввода имени на странице регистрации
    private final By nameField = By.xpath(".//label[text()='Имя']/../input[@class='text input__textfield text_type_main-default']");

    // Локатор поля ввода Email на странице регистрации
    private final By emailField = By.xpath(".//label[text()='Email']/../input[@class='text input__textfield text_type_main-default']");

    // Локатор поля ввода пароля на странице регистрации
    private final By passwordField = By.xpath(".//label[text()='Пароль']/../input[@class='text input__textfield text_type_main-default']");

    //Локатор кнопки "Зарегистрироваться" на странице регистрации
    private final By registerButton = By.xpath(".//button[text()='Зарегистрироваться']");

    //Локатор кнопки "Войти" на странице регистрации
    private final By loginButton = By.xpath(".//a[text() = 'Войти']");

    //Локатор текста "Некорректный пароль" на странице регистрации (текст проявляется при некорректном пароле)
    private final By errorMassagePasswordField = By.xpath(".//p[text() = 'Некорректный пароль']");

    public RegistrationPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    // Ожидание загрузки страницы формы регистрации
    public RegistrationPage waitLoadRegistrationPage() {
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.visibilityOfElementLocated(titleRegistration));
        return this;
    }

    @Step("Заполнить поле Имя на экране регистрации")
    public RegistrationPage inputNameField(String name) {
        driver.findElement(nameField).sendKeys(name);
        return this;
    }

    @Step("Заполнить поле Email на экране регистрации")
    public RegistrationPage inputEmailField(String email) {
        driver.findElement(emailField).sendKeys(email);
        return this;
    }

    @Step("Заполнить поле Пароль на экране регистрации")
    public RegistrationPage inputPasswordField(String password) {
        driver.findElement(passwordField).sendKeys(password);
        return this;
    }

    @Step("Клик на кнопку 'Зарегистрироваться'")
    public AuthorizationPage clickRegisterButton() {
        driver.findElement(registerButton).click();
        return new AuthorizationPage(driver);
    }

    @Step("Клик на кнопку 'Войти'")
    public AuthorizationPage clickLoginLink() {
        driver.findElement(loginButton).click();
        return new AuthorizationPage(driver);
    }

    @Step("Проверить наличие ошибки 'Некорректный пароль', в форме регистрации пользователя")
    public boolean errorMessagePasswordFieldIsDisplayed() {
        clickRegisterButton(); // Снять фокус с поля кликом на кнопку <<Зарегистрироваться>>, после ввода некорректного пароля
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.visibilityOfElementLocated(errorMassagePasswordField));
        return driver.findElement(errorMassagePasswordField).isDisplayed();
    }
}
