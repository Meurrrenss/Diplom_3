package site.nomoreparties.stellarburgers.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPage extends Header {
    private final WebDriver driver;

    private final String PAGE_URL = "https://stellarburgers.nomoreparties.site/";

    // Локатор текста "Соберите бургер"
    private final By titleAssembleBurger = By.xpath(".//h1[text()='Соберите бургер']");

    // Локатор кнопки "Войти в аккаунт"
    private final By loginButton = By.xpath(".//button[text() = 'Войти в аккаунт']");

    // Локатор кнопки "Оформить заказ" (кнопка отображается у авторизованного пользователя на месте кнопки "Войти в аккаунт")
    private final By orderButton = By.xpath(".//button[text() = 'Оформить заказ']");

    // Локатор таба "Булки" (таб выбран)
    private final By tubBunsChosen = By.xpath(".//div[@class = 'tab_tab__1SPyG tab_tab_type_current__2BEPc pt-4 pr-10 pb-4 pl-10 noselect']/span[text() = 'Булки']");

    //Локатор таба "Булки" (таб не выбран)
    private final By tubBunsNotChosen = By.xpath(".//div[@class = 'tab_tab__1SPyG  pt-4 pr-10 pb-4 pl-10 noselect']/span[text() = 'Булки']");

    // Локатор таба "Соусы" (таб выбран)
    private final By tubSaucesChosen = By.xpath(".//div[@class = 'tab_tab__1SPyG tab_tab_type_current__2BEPc pt-4 pr-10 pb-4 pl-10 noselect']/span[text() = 'Соусы']");

    // Локатор таба "Соусы" (таб не выбран)
    private final By tubSaucesNotChosen = By.xpath(".//div[@class = 'tab_tab__1SPyG  pt-4 pr-10 pb-4 pl-10 noselect']/span[text() = 'Соусы']");

    // Локатор таба "Начинки" (таб выбран)
    private final By tubFillingsChosen = By.xpath(".//div[@class = 'tab_tab__1SPyG tab_tab_type_current__2BEPc pt-4 pr-10 pb-4 pl-10 noselect']/span[text() = 'Начинки']");

    // Локатор таба "Начинки" (таб не выбран)
    private final By tubFillingsNotChosen = By.xpath(".//div[@class = 'tab_tab__1SPyG  pt-4 pr-10 pb-4 pl-10 noselect']/span[text() = 'Начинки']");

    public MainPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    @Step("Открыть главную страницу сервиса")
    public MainPage open() {
        driver.get(PAGE_URL);
        return this;
    }

    // Ожидание загрузки главной страницы
    public MainPage waitLoadHomePage() {
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.visibilityOfElementLocated(titleAssembleBurger));
        return this;
    }


    @Step("Кликнуть на кнопку 'Войти в аккаунт' на главной странице")
    public AuthorizationPage clickLoginButton() {
        driver.findElement(loginButton).click();
        return new AuthorizationPage(driver);
    }

    @Step("Проверить наличие кнопки 'Оформить заказ' после авторизации")
    public boolean checkOutOrderButtonIsDisplayed() {
        return driver.findElement(orderButton).isDisplayed();
    }

    @Step("Проверить наличие заголовка конструктора 'Соберите бургер'")
    public boolean titleAssembleBurgerIsDisplayed() {
        return driver.findElement(titleAssembleBurger).isDisplayed();
    }

    @Step("Клик на таб 'Булки'")
    public MainPage clickBunSection() {
        driver.findElement(tubBunsNotChosen).click();
        return this;
    }

    @Step("Клик на таб 'Соусы'")
    public MainPage clickSauceSection() {
        driver.findElement(tubSaucesNotChosen).click();
        return this;
    }

    @Step("Клик на таб 'Начинки'")
    public MainPage clickFilingSection() {
        driver.findElement(tubFillingsNotChosen).click();
        return this;
    }

    @Step("Убедиться, что после клика на таб 'Булки', он изменил состояние на 'выбран'")
    public boolean bunsSectionIsActive() {
        return driver.findElement(tubBunsChosen).isDisplayed();
    }

    @Step("Убедиться, что после клика таб 'Соусы', он изменил состояние на 'выбран'")
    public boolean saucesSectionIsActive() {
        return driver.findElement(tubSaucesChosen).isDisplayed();
    }

    @Step("Убедиться, что после клика таб 'Начинки', он изменил состояние на 'выбран'")
    public boolean filingsSectionIsActive() {
        return driver.findElement(tubFillingsChosen).isDisplayed();
    }
}
