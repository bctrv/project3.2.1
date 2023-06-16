package ru.netology.data;

import com.github.javafaker.Faker;
import lombok.Value;

import java.util.Locale;

public class DataHelper {

    private DataHelper() {
    }

    private static Faker faker = new Faker(new Locale("en"));

    @Value
    public static class AuthInfo {
        private String login;
        private String password;
    }
    @Value
    public static class InvalidAuthInfo {
        private String login;
        private String randomPassword;
    }

    public static AuthInfo getAuthInfo() {
        return new AuthInfo("vasya", "qwerty123");
    }

    private static String randomLogin() {
        return faker.name().username();
    }

    private static String randomPassword() {
        return faker.internet().password();
    }

    public static InvalidAuthInfo getInvalidInfo() {
        return new InvalidAuthInfo("vasya", randomPassword());
    }

    @Value
    public static class VerificationCode {
        private String code;
    }

    public static VerificationCode getRandomVerificationCode() {
        return new VerificationCode(faker.numerify("#####"));
    }
}
