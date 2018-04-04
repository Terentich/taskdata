# Настройка базы данных
## Создание схемы
Создайте базу данных:
    create database timetrackerdb;
    
Создайте пользователя:    
    create user timetracker with password 'password';

Назначьте права пользователю для созданной базы данных:
    grant all privileges on database "timetrackerdb" to timetracker;
## В файле application.properties пропишите:

Подробная информация:
https://docs.spring.io/spring-boot/docs/current/reference/html/boot-features-sql.html

Demo - create demo db and dummy data