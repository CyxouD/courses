package com.courses.spalah;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * @author Ievgen Tararaka
 */
public class Reflection {
    public static void main(String[] args) throws ClassNotFoundException {
        Class<Reflection> clazz = Reflection.class; // на этапе компиляции

        Class clazzRuntime = Class.forName("com.courses.spalah.Reflection"); // в runtim'e
        // Необходимо указывать полное имя класса. Если класс не будет найдет - вылетит ClassNotFoundException

        String fullName = clazz.getName();
        // com.courses.spalah.Reflection

        String simpleName = clazzRuntime.getSimpleName();
        // Reflection

        for (Method m : SampleClass.class.getMethods()) {
            System.out.println(m.toString());
        }
    }

    public static void sample() {
        Class<SampleClass> clazz = SampleClass.class;
        int modifiers = clazz.getModifiers(); // все модификаторы хранятся в виде битовой маски в переменной типа int

        Modifier.isAbstract(modifiers);
        Modifier.isFinal(modifiers);
        Modifier.isInterface(modifiers);
        Modifier.isNative(modifiers);
        Modifier.isPrivate(modifiers);
        Modifier.isProtected(modifiers);
        Modifier.isPublic(modifiers);
        Modifier.isStatic(modifiers);
        Modifier.isStrict(modifiers);
        Modifier.isSynchronized(modifiers);
        Modifier.isTransient(modifiers);
        Modifier.isVolatile(modifiers);

        Class superClass = clazz.getSuperclass();
        Class superSuperClass = superClass.getSuperclass();
        // ...  так пока не дойдем до Object
        Class object = superSuperClass.getSuperclass(); // object = null

        Class[] interfaces = clazz.getInterfaces(); // список всех интерфейсов, которые имплементят данный класс
        // массив содержит только те классы которые исплементятся напрямую
        // не включая родительские имплементации

        Constructor[] constructors = clazz.getConstructors();
        // все конструкторы класса (с параметрами и без)

        Method[] methods = clazz.getMethods();
        // методы класса

        Field[] method = clazz.getFields();
        // поля класса

        Annotation[] annotations = clazz.getAnnotations();
        // аннотации класса
    }

    public static void constructors() throws IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException {
        Class clazz = SampleClass.class;
        Constructor[] constructors = clazz.getConstructors(); // получение конструкторов

        Object instance;
        for (Constructor c : constructors) {
            if (c.getParameterTypes().length == 0) { // получение параметров конструктора
                instance = c.newInstance(); // создание объекта
            }
        }

        Constructor constructor = clazz.getConstructor(long.class, int.class, String.class, boolean.class); // получение конкретного конструктора
        instance = constructor.newInstance(1, 1, "", false);  // создание обьекта
    }

    public static void fields() throws NoSuchFieldException, IllegalAccessException {
        Class clazz = SampleClass.class;
        Field[] fields = clazz.getFields(); // получение всех филдов класса
        SampleClass instance = new SampleClass();

        Field concreateField = clazz.getField("publicLong"); // получение конкретного филда класса по его имени

        for (Field f : fields) {
            System.out.println(f.getName()); // имя филда
        }

        long value = (long) concreateField.get(instance); // получение значения поля
        concreateField.set(instance, value + 1); // присвоение полю нового значения

        Field staticField = clazz.getField("static_char"); // статическое поле
        char char_val = (char) staticField.get(null); // получение значения статического поля
    }

    public static void methods() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Class clazz = SampleClass.class;
        Method[] methods = clazz.getMethods(); // получение всех методов класса
        SampleClass instance = new SampleClass();

        for (Method m : methods) {
            m.getParameterTypes(); // получение параметров метода
            m.getReturnType(); // получение типа возвращаемого значения
        }

        Method method = clazz.getMethod("setPrivateBoolean", boolean.class); // получение метода по имени и аргументам

        Method methodWithoutParams = clazz.getMethod("toString", null);
        // получение метода без аргументов

        Method staticMethod = clazz.getMethod("newInstance", null); // получение статического метода без аргуметов

        method.invoke(instance, true); // вызов метода у экземпляра instance
        SampleClass newInstance = (SampleClass) staticMethod.invoke(null, null); // вызов статического метода без параметров
    }

    public static void privateMethods() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Class clazz = SampleClass.class;
        Method[] methods = clazz.getDeclaredMethods(); // получение всех методов класса, включая private
        SampleClass instance = new SampleClass();

        Method privateMethod = clazz.getDeclaredMethod("isPrivateBoolean", null); // получаем метод
        privateMethod.setAccessible(true); // делаем поле доступным
        boolean value = (boolean) privateMethod.invoke(instance, null); // успешно вызываем приватный метод
    }

    public static void privateFields() throws NoSuchFieldException, IllegalAccessException {
        Class clazz = SampleClass.class;
        Field[] fields = clazz.getDeclaredFields(); // получение всех полей класса, включая приватные
        SampleClass instance = new SampleClass();

        Field privateField = clazz.getDeclaredField("privateBoolean");
        privateField.setAccessible(true); // делаем поле доступным
        boolean value = (boolean) privateField.get(instance); // берем значение у приватного поля
    }

    public static void annotations() throws NoSuchMethodException {
        Class clazz = SampleClass.class;
        Annotation[] annotations = clazz.getAnnotations(); // массив всех аннотаций на классе

        for (Annotation a : annotations) {
            if (a instanceof SampleAnnotation) {
                ((SampleAnnotation) a).number(); // получение значений
                ((SampleAnnotation) a).value();
            }
        }

        Annotation annotation = clazz.getAnnotation(SampleAnnotation.class); // получение аннотаций по типу
        if (annotation instanceof SampleAnnotation) {
            ((SampleAnnotation) annotation).number();
            ((SampleAnnotation) annotation).value();
        }

        Method[] methods = clazz.getDeclaredMethods(); // получаем методы
        for (Method m : methods) {
            for (Annotation a : m.getAnnotations()) { // берем аннотации у метода
                if (a instanceof Deprecated) {
                    // чет с ними делаем
                }
            }
        }

        Method method = clazz.getMethod("isPrivateBoolean", null); // берем метод по имени и агрументам
        Annotation annotation1 = method.getAnnotation(SampleAnnotation.class); // берем у него аннотацию
    }

    public static void classloading() {
        ClassLoader classLoader = Reflection.class.getClassLoader(); // получаем ClassLoader

        try {
            Class clazz = classLoader.loadClass("com.courses.spalah.SampleClass"); // загружаем класс
            System.out.println("aClass.getName() = " + clazz.getName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
