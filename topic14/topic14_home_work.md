## Домашнее задание №14

### Изучаем Сервлеты и смежную информацию
 * TCP/API
 * HTTP
 * Servlet API
   + Читаем книжки\статейки
   + Туториалы
     - [Tutorialspoint](http://www.tutorialspoint.com/servlets/)
     - [Jenkov](http://tutorials.jenkov.com/java-servlets/index.html)
 * Servlet containers

### Пишем web приложение, которое базируется на [ДЗ #12](https://github.com/rxn1d/courses/blob/master/topic12/topic12_home_work.md)
 * Приложение должно уметь:
   + Уметь работать с базой данной, лажащей в основе предыдущего ДЗ
   + Возвращать список всех объявлений в формате JSON* или XML*
   + Делать поиск по параметрам (производитель, модель, год, цена)
   + Добавлять новое объявление

#### Получение списка всех обьявлений
 * `URL = /ads`
 * `HTTP request method = GET`
 * Пример:
  ```
     Request: GET-> /ads

     Response:
     Status Code: 200 OK
     {
       [
         {
           "id": 1,
           "manufacturer": "Mazda",
           "modelName": "Mazda3",
           "year": 2011,
           "vin": "ABC123456",
           "description": "Test",
           "price": 9999,
           "contact_phone": "+380-66-22-333"
          },
         {
           "id": 2,
           "manufacturer": "Suzuki",
           "modelName": "Swift",
           "year": 2007,
           "vin": "111112313AAWZ",
           "description": "Test",
           "price": 8000,
           "contact_phone": "+380-99-22-333"
         },
         . . .
       ]
     }
  ```
#### Поиск объявления
 * `URL = /ads?{manufacturer}&{modelName}&{yearFrom}&{yearTo}&{priceFrom}&{priceTo}`
 * `HTTP request method = GET`
 * Пример:
 ```
    Request: method GET-> /ads?manufacturer=Mazda&modelName=Mazda3&yearFrom=2000&yearTo=2016&priceFrom=1000&priceTo=10000

    Response:
    Status Code: 200 OK
    {
      [
        {
          "id": 1,
          "manufacturer": "Mazda",
          "modelName": "Mazda3",
          "year": 2011,
          "vin": "ABC123456",
          "description": "Test",
          "price": 9999,
          "contact_phone": "+380-66-22-333"
        }
      ]
    }
 ```

#### Добавление объявления (с сохранением в БД)
 * `URL = /ads`
 * `HTTP request method = POST`
 * Пример:
  ```
     Request: POST-> /ads
     Body:
       {
         "manufacturer": "Mazda",
         "modelName": "Mazda3",
         "year": 2011,
         "vin": "ABC123456",
         "description": "Test",
         "price": 9999,
         "contact_phone": "+380-66-22-333"
       }

     Response:
     Status Code: 200 OK
     {}
  ```

<br>* Сериализацию\десериализацию можно делать с помощью внешних бибилиотек
<br>* Зависимости на внешние библиотеки добавляем в pom.xml
<br>* Веб форма не обязательна
<br>* Приложение запускаем на Tomcat'е

