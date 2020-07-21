# Задача №1 - Скоро deadline

## Краткое описание

Для запуска SUT используется файл app-start.cmd, в котором, после окончания рабоботы SUT выполняется удаление всех 
записей таблиц users и cards. Это позволяет запускать SUT многокоатно.

```
String sql = "INSERT INTO users (username, password) VALUES (?, ?)";
PreparedStatement stmt = con.prepareStatement(sql);
stmt.setInt(1, Integer.parseInt(userNameT.getText()));
stmt.setString(2, DigestUtils.sha1Hex(String.valueOf(userPassT.getPassword())));
```

```$xslt
INSERT INTO UserNameAndPasswordDemo(UserId, UserPassword) VALUES ('John@gg.com', MD5('john123'));
```