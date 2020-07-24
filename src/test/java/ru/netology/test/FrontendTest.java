package ru.netology.test;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import lombok.val;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.DbHelper;
import ru.netology.page.DashboardPage;
import ru.netology.page.LoginPage;

import static com.codeborne.selenide.Selenide.open;

public class FrontendTest {
    private LoginPage loginPage;

    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @BeforeEach
    public void openPage() {
        loginPage = open("http://localhost:9999", LoginPage.class);
    }

    @AfterAll
    public static void postConditions() {
        DbHelper.clearAuthCodesTable();
    }

    @Test
    public void openDashboardWithValidAuthInfo() {
        val authInfo = DbHelper.getValidAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        DashboardPage page = verificationPage.validVerify(DbHelper.getVerificationCode(authInfo));
    }

    @Test
    public void shouldNotBlockedWithInvalidPasswordAfterThreeAttempts() {
        loginPage.invalidLogin();
    }
}
