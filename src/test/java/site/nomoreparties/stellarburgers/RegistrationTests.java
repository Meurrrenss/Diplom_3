package site.nomoreparties.stellarburgers;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import site.nomoreparties.stellarburgers.api.client.UserClient;
import site.nomoreparties.stellarburgers.api.usercreator.UserCreator;
import site.nomoreparties.stellarburgers.api.userconstructor.User;
import site.nomoreparties.stellarburgers.api.userconstructor.UserCredentials;
import site.nomoreparties.stellarburgers.drivers.WebDriverConstructor;
import site.nomoreparties.stellarburgers.pages.MainPage;
import site.nomoreparties.stellarburgers.pages.ProfilePage;
import site.nomoreparties.stellarburgers.pages.RegistrationPage;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@DisplayName("Регистрация")
public class RegistrationTests {
    private WebDriver driver;
    private RegistrationPage registrationFormPage;
    private User user;
    private final static UserClient userClient = new UserClient();
    boolean skipDeleteUser = false;

    @Before
    public void setUp() {
        WebDriverConstructor webDriverConstructor = new WebDriverConstructor();
        driver = webDriverConstructor.getWebDriver();
        user = UserCreator.randomUser();
        registrationFormPage = new MainPage(driver)
                .open()
                .waitLoadHomePage()
                .clickPersonalAccountButtonWithoutAuthorization()
                .clickRegisterLink();
    }

    @After
    public void tearDown() {
        driver.quit();
        if (!skipDeleteUser) {
            ValidatableResponse loginResponse = userClient.loginUser(UserCredentials.from(user));
            String accessToken = userClient.getAccessToken(loginResponse);
            userClient.deleteUser(accessToken);
        }
    }

    @Test
    @DisplayName("Успешная регистрация")
    public void successfulRegistrationTest() {
        ProfilePage personalAccountPage = registrationFormPage
                .inputNameField(user.getName())
                .inputEmailField(user.getEmail())
                .inputPasswordField(user.getPassword())
                .clickRegisterButton()
                .waitLoadPageAuthorizationForm()
                .fillInTheEmailField(user.getEmail()) // Авторизация созданным пользователем
                .fillInThePasswordField(user.getPassword())
                .clickEnterButton()
                .waitLoadHomePage()
                .clickPersonalAccountButtonWithAuthorization()
                .waitLoadPagePersonalAccount();

        String actualName = personalAccountPage.getNameData(); // Проверили, что в личном кабинете отображается имя зарегистрированного пользователя
        assertEquals(user.getName(), actualName);
        String actualLogin = personalAccountPage.getLoginData(); // Проверили, что в личном кабинете отображается почта зарегистрированного пользователя
        assertEquals(user.getEmail(), actualLogin);
    }

    @Test
    @DisplayName("Ошибка некорректного пароля")
    @Description("Минимальный пароль 6 символов")
    public void errorWithInvalidPasswordTest() {
        user.setPassword(UserCreator.invalidRandomPassword());
        boolean errorIsDisplayed = registrationFormPage
                .waitLoadRegistrationPage()
                .inputNameField(user.getName())
                .inputEmailField(user.getEmail())
                .inputPasswordField(user.getPassword())
                .errorMessagePasswordFieldIsDisplayed();
        assertTrue(errorIsDisplayed); // Проверили, что при вводе некорректного пароля отображается ошибка
        skipDeleteUser = true;
    }
}
