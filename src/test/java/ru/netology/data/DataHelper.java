package ru.netology.data;

import com.github.javafaker.Faker;
import lombok.SneakyThrows;
import lombok.Value;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.util.Locale;

import static ru.netology.data.SQLHelper.getConn;
import static ru.netology.data.SQLHelper.runner;

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

    @Value
    public static class VerificationCode {
        private String code;
    }

    private static String getRandomPassword() {
        return faker.internet().password();
    }

    public static AuthInfo generateInvalidUser() {
        return new AuthInfo(getAuthInfo().getLogin(), getRandomPassword());
    }

    @SneakyThrows
    public static String blockedUser() {
        var blockingStatus = "SELECT status FROM users LIMIT 1";
        var conn = getConn();
        var result = runner.query(conn, blockingStatus, new ScalarHandler<String>());
        return result;

    }
}
