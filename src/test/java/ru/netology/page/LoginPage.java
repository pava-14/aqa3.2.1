package ru.netology.page;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;
import ru.netology.data.DbHelper;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

public class LoginPage {
    private SelenideElement loginField = $("[data-test-id=login] input");
    private SelenideElement passwordField = $("[data-test-id=password] input");
    private SelenideElement loginButton = $("[data-test-id=action-login]");

    public VerificationPage validLogin(DbHelper.AuthInfo info) {
        loginField.setValue(info.getLogin());
        passwordField.setValue(info.getPassword());
        loginButton.click();
        return page(VerificationPage.class);
    }

    public void invalidLogin() {

        loginField.doubleClick();
        loginField.sendKeys(Keys.BACK_SPACE);
        loginField.setValue(DbHelper.generateAuthInfo().getLogin());
        for (int i = 0; i < 5; i++) {
            passwordField.doubleClick();
            passwordField.sendKeys(Keys.BACK_SPACE);
            passwordField.setValue(DbHelper.generateAuthInfo().getPassword());
            loginButton.click();
        }
        $(withText("Неверно")).waitUntil(visible, 15000);
    }
}
