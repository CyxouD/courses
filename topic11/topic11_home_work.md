## Домашнее задание №11

### Продолжаем читать про SQL и базы данных
 * Что такое база данных
 * Какие они бывают
 * DDL
 * DML
 * CRUD операции
 * My SQL
 * Нормализация

### Устанавливаем [MySQL Community Edition (GPL)](http://dev.mysql.com/downloads/) (это сервер)
### Устанавливаем любой SQL клиент ([Heidi](http://www.heidisql.com/), [DBeaver](http://dbeaver.jkiss.org/), [My SQL Workbench](http://dev.mysql.com/downloads/workbench/), [DataGrip](https://www.jetbrains.com/datagrip/) и т.д.)
 * Создаем базу данных test
 * В ней создаем 2 таблицы
   + person
      * id (целый тип, первичный ключ)
      * first_name (строковый тип)
      * last_name (строковый тип)
      * address_id (целый тип, внешний ключ на таблицу address)

      | id | first | last_name | address_id |
      | ---| ---   | ---       | ---        |
   + address
      * id (целый тип, первичный ключ)
      * country (строковый тип)
      * city (строковый тип)
      * address (строковый тип)

       | id | country | city | address |
       | ---| ---     | ---  | ---     |
 * Создаем тестовые данные в обоих таблицах (минимум по 10 записей)
 * Таблица person обязательно должна содержать записи, которые ссылаются на таблицу address
 * Играем с SQL. Попробовать запросы:
   + INSERT
   + UPDATE
   + DELETE
   + TRUNCATE
   + INSERT REPLACE
   + MODIFY TABLE

