# Задача №1 - Скоро deadline

## Краткое описание

```
String sql = "INSERT INTO users (username, password) VALUES (?, ?)";
PreparedStatement stmt = con.prepareStatement(sql);
stmt.setInt(1, Integer.parseInt(userNameT.getText()));
stmt.setString(2, DigestUtils.sha1Hex(String.valueOf(userPassT.getPassword())));
```

```$xslt
INSERT INTO UserNameAndPasswordDemo(UserId, UserPassword) VALUES ('John@gg.com', MD5('john123'));
```