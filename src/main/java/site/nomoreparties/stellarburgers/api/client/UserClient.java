package site.nomoreparties.stellarburgers.api.client;

import com.google.gson.Gson;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import site.nomoreparties.stellarburgers.api.userconstructor.User;
import site.nomoreparties.stellarburgers.api.userconstructor.UserCredentials;

import static io.restassured.RestAssured.given;

public class UserClient extends RestClient {

    private static final String CREATE_USER_ENDPOINT = "/api/auth/register";
    private static final String DELETE_USER_DATA_ENDPOINT = "/api/auth/user";
    private static final String AUTHORIZATION_USER_ENDPOINT = "/api/auth/login";

    @Step("Создать пользователя через API")
    public ValidatableResponse createUser(User user) {
        String requestBody = new Gson().toJson(user);
        Allure.addAttachment("Request body", "application/json", requestBody);
        return given()
                .spec(specContentType())
                .body(user)
                .when()
                .post(CREATE_USER_ENDPOINT)
                .then();
    }

    @Step("Получить accessToken через API")
    public String getAccessToken(ValidatableResponse loginResponse) {
        return loginResponse.extract().jsonPath().getString("accessToken");
    }

    @Step("Авторизоваться пользователем через API")
    public ValidatableResponse loginUser(UserCredentials userCredentials) {
        String requestBody = new Gson().toJson(userCredentials);
        Allure.addAttachment("Request body", "application/json", requestBody);
        return given()
                .spec(specContentType())
                .body(userCredentials)
                .post(AUTHORIZATION_USER_ENDPOINT)
                .then();
    }

    @Step("Удалить пользователя через API")
    public void deleteUser(String accessToken) {
        given()
                .spec(specWithoutContentType())
                .header("Authorization", accessToken)
                .delete(DELETE_USER_DATA_ENDPOINT)
                .then();

    }


}