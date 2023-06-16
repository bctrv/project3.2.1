package ru.netology.test;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.data.SQLHelper;
import ru.netology.page.LoginPage;

import static com.codeborne.selenide.Selenide.open;
import static ru.netology.data.SQLHelper.cleanDB;

public class BankTest {

    @AfterAll
    static void teardown() {
        cleanDB();
    }

    @Test
    void successfullLogin() {
        var loginPage = open("http://localhost:9999", LoginPage.class);
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        verificationPage.verifyVerificationPageVisible();
        var verificationCode = SQLHelper.getVerificationCode();
        verificationPage.validVerify(verificationCode.getCode());
    }

    @Test
    void unsuccessfullLogin() {
        var loginPage = open("http://localhost:9999", LoginPage.class);
        var randomUser = DataHelper.getInvalidInfo();
        var invalidLogin = loginPage.invalidLogin(randomUser);
        invalidLogin.invalidLoginNotification();
    }

}
