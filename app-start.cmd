java -jar ./app-deadline.jar -P:jdbc.url=jdbc:mysql://localhost:3306/app -P:jdbc.user=app -P:jdbc.password=pass
winpty docker container exec -it dbc mysql -u app -ppass app -e "SET FOREIGN_KEY_CHECKS = 0;TRUNCATE TABLE users;SET FOREIGN_KEY_CHECKS = 1;"
winpty docker container exec -it dbc mysql -u app -ppass app -e "TRUNCATE TABLE cards;"



