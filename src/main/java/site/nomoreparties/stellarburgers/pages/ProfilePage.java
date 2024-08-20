package site.nomoreparties.stellarburgers.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProfilePage extends Header {
    private final WebDriver driver;

    // Локатор поля ввода имени на стринице профиля
    private final By userNameField = By.xpath(".//label[text() ='Имя']/../input[@class='text input__textfield text_type_main-default input__textfield-disabled']");

    // Локатор поля ввода логина на стринице профиля
    private final By userLoginField = By.xpath(".//label[text() ='Логин']/../input[@class='text input__textfield text_type_main-default input__textfield-disabled']");

    //Локатор блока с кнопками (Профиль, История заказов, Выход) на стринице профиля
    private final By blockWithButtonsProfile = By.xpath(".//ul[@class = 'Account_list__3KQQf mb-20']");

    // Локатор кнопки "Выход" на стринице профиля
    private final By exitButton = By.xpath(".//button[text() = 'Выход']");

    //Локатор текста "В этом разделе вы можете изменить свои персональные данные" на стринице профиля
    private final By descriptionSection = By.xpath(".//p[text() = 'В этом разделе вы можете изменить свои персональные данные']");


    public ProfilePage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    // Ожидание загрузки страницы личного кабинета
    public ProfilePage waitLoadPagePersonalAccount() {
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.visibilityOfElementLocated(descriptionSection));
        return this;
    }

    @Step("Получить информацию об имени, из профиля зарегистрированного пользователя")
    public String getNameData() {
        return driver.findElement(userNameField).getAttribute("value");
    }

    @Step("Получить информацию о логине(почте), из профиля зарегистрированного пользователя")
    public String getLoginData() {
        return driver.findElement(userLoginField).getAttribute("value");
    }

    @Step("Убедиться, что перешли в личный кабинет пользователя")
    public boolean userProfileIsDisplayed() {
        return driver.findElement(blockWithButtonsProfile).isDisplayed();
    }

    @Step("Клик на кнопку 'Выход'")
    public AuthorizationPage clickExitButton() {
        driver.findElement(exitButton).click();
        return new AuthorizationPage(driver);
    }
}
