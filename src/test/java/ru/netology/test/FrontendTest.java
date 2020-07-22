package ru.netology.test;

import lombok.val;
import org.junit.jupiter.api.Test;
import ru.netology.data.DbHelper;
import ru.netology.page.DashboardPage;
import ru.netology.page.LoginPage;

import java.sql.SQLException;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class FrontendTest {

    @Test
    public void openDashboardWithValidLogin() {
        val loginPage = open("http://localhost:9999", LoginPage.class);
        val authInfo = DbHelper.getValidAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        DbHelper.VerificationCode verificationCode = null;
        try {
            verificationCode = DbHelper.getVerificationCode(authInfo);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        DashboardPage page = verificationPage.validVerify(verificationCode);
    }

    @Test
    public void shouldOpenDashboardWithInvalidAuthInfo() {
        val loginPage = open("http://localhost:9999", LoginPage.class);
        val authInfo = DbHelper.generateAuthInfo();
        loginPage.invalidLogin(authInfo);
    }

    @Test
    public void shouldOpenDashboardWithInvalidCode() {
        val loginPage = open("http://localhost:9999", LoginPage.class);
        val authInfo = DbHelper.getValidAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        verificationPage.invalidVerify(DbHelper.generateVerificationCode());
    }

    @Test
    public void shouldOpenDashboardWithInvalidAuthInfoWithThreeRepeat() {
        val loginPage = open("http://localhost:9999", LoginPage.class);
        val authInfo = DbHelper.generateAuthInfo();
        int repeatCount = 1;
        int expected = 4;
        while (repeatCount <= expected) {
            loginPage.invalidLogin(authInfo);
            repeatCount++;
        }
        assertEquals(expected, repeatCount - 1);
    }
}
