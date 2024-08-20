package site.nomoreparties.stellarburgers.api.usercreator;

import com.github.javafaker.Faker;
import org.apache.commons.lang3.RandomStringUtils;
import site.nomoreparties.stellarburgers.api.userconstructor.User;

public class UserCreator {

    private static final Faker faker = new Faker();

    public static User randomUser() {
        final String email = faker.internet().emailAddress();
        final String password = faker.internet().password();
        final String name = faker.name().firstName();
        return new User(email, password, name);
    }

    public static String invalidRandomPassword() {
        int length = (int) (Math.random() * 6);
        return RandomStringUtils.random(length, true, true);
    }
}