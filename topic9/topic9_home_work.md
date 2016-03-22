## Домашнее задание №9

### Читаем
 * Читаем про обощения (generics)

### Имлементим мультимапу
 * Суть мультимапы в том, что по она по ключу хранит коллекцию значений а не единственное значение. Например:
   ```
     multiMap = MultiMapFactory.createMultiMap(); // []
     multiMap.put("fruits", "apple"); // [fruits - [apple]]
     multiMap.put("fruits", "banana"); // [fruits - [apple, banana]]
     multiMap.put("fruits", "pear"); // [fruits - [apple, banana, pear]]

     Collection<String> fruits = multiMap.get("fruits"); // [apple, banana, pear]
   ```
 * Интерфейс `MultiMap` содержит в себе методы, которые необходимо реализовать
 * **Обязательно следим за тем, чтобы проходили тесты**