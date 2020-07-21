package ru.netology.data;

import com.github.javafaker.Faker;
import lombok.Value;
import lombok.val;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Locale;

public class DbHelper {
    private DbHelper() {
    }

    @Value
    public static class AuthInfo {
        //        String id;
        String login;
        String password;
//        String status;
    }

    public static AuthInfo getAuthInfo() {
        return new AuthInfo("vasya", "qwerty123");
    }

//    public static AuthInfo generateAuthInfo() {
//        Faker faker = new Faker(new Locale("ru"));
//        return new AuthInfo(
//                faker.name().username(),
//                faker.internet().password()
//        );
//    }

    @Value
    public static class VerificationCode {
        String code;
    }

    public static VerificationCode getVerificationCode() throws SQLException {
//        TODO:
        val usersSQL = "SELECT * FROM users;";
        val runner = new QueryRunner();

        try (
                val conn = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/app", "app", "pass"
                );
        ) {

            val first = runner.query(conn, usersSQL, new BeanHandler<>(User.class));
            System.out.println(first);
            val all = runner.query(conn, usersSQL, new BeanListHandler<>(User.class));
            System.out.println(all);
        }
        return new VerificationCode("12345");
    }

    public static VerificationCode generateVerificationCode() {
        Faker faker = new Faker(new Locale("ru"));
        return new VerificationCode(
                faker.number().digits(5)
        );
    }
}
