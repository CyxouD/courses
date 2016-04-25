## Домашнее задание №15

### Читаем про рефлексию
 * Дополнительно смотрим:
   + Dynamic class reloading
   + Generics
   + Arrays
   + Proxies

### Доделываем предыдущую домашку

### *Маленькое задание на работу с аннотациями
 * Делаем сервис который умеет сохранять сущностями в базу по их филдам и названию таблицы
 * Пример:
    ```
      class Car {
        private long id;
        private int price;
        private String modelName;
        rpviate String manufacturer;
      }

      main() {
        Car car = new Car();

        ReflectioPersistent rf = new ReflectioPersistent();
        rf.store("cars", car); // запись в базу в таблицу cars
      }
    ```

#### * для тех, у кого все готово