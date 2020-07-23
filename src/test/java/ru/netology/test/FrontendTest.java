package ru.netology.test;

import lombok.val;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import ru.netology.data.DbHelper;
import ru.netology.page.DashboardPage;
import ru.netology.page.LoginPage;

import java.sql.SQLException;

import static com.codeborne.selenide.Selenide.open;

public class FrontendTest {

    @AfterAll
    public static void PostConditions() throws SQLException {
        DbHelper.ClearAuthCodesTable();
    }

    @Test
    public void openDashboardWithValidAuthInfo() {
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
    public void shouldNotBlockedWithInvalidPasswordAfterThreeAttempts() {
        val loginPage = open("http://localhost:9999", LoginPage.class);
        loginPage.invalidLogin();
    }
}
