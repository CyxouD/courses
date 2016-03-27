## Домашнее задание №12
 * Книжечка по SQL:
   +  Head First SQL Your Brain on SQL - A Learner's Guide By Lynn Beighley - [ссылка](http://shop.oreilly.com/product/9780596526849.do)

### JOIN'ы
 * Wikipedia - [ссылка](https://en.wikipedia.org/wiki/Join_(SQL))
 * Joins Explained - [ссылка](http://www.sql-join.com/)
 * w3schools
   + Joins - [ссылка](http://www.w3schools.com/sql/sql_join.asp)
   + Inner Join - [ссылка](http://www.w3schools.com/sql/sql_join_inner.asp)
   + Left Join - [ссылка](http://www.w3schools.com/sql/sql_join_left.asp)
   + Right Join - [ссылка](http://www.w3schools.com/sql/sql_join_right.asp)
   + Full Join - [ссылка](http://www.w3schools.com/sql/sql_join_full.asp)

### Транзакционность
 * Tutorialspoint - [ссылка](http://www.tutorialspoint.com/sql/sql-transactions.htm)
 * MySQL - [ссылка](http://dev.mysql.com/doc/refman/5.7/en/commit.html)

### Хранимые процедуры
 * MySQL - [ссылка](https://dev.mysql.com/doc/connector-net/en/connector-net-tutorials-stored-procedures.html)
 * TutsPlus - [ссылка](http://code.tutsplus.com/articles/an-introduction-to-stored-procedures-in-mysql-5--net-17843)

### Читаем про JDBC
 * Jenkov Tutorial - [ссылка](http://tutorials.jenkov.com/jdbc/index.html)
 * Tutorialspoint - [ссылка](http://www.tutorialspoint.com/jdbc/jdbc-quick-guide.htm)

### Делаем десктопное GUI приложение `Car Marketplace` (на Swing'е конечно)
 * Приложение является торговой площадкой для подержанных автомобилей
 * Каждое обьявление имеет атрибуты:
   + Автомобиль
   + Цена
 * Каждый автомобиль имеет атрибуты:
   + Владелец(пользователь)
   + Производитель
   + Модель
   + Год выпуска
   + VIN код
   + Описание
 * Каждый пользователь(он же владелец) имеет атрибуты:
   + Имя
   + Фамилия
   + Контактный телефон
 * Приложение должно уметь:
   + Отдавать список обьявлений с автом, их ценами, описанием, контактами владельца
   + Добавлять новое обьявление (как будет выглядеть форма добавление - все равно, главное чтобы записи сохранялись во всех таблицах)
   + Удалять обьявления
   + Производить поиск по имени модели, производителю, году выпуска, цене