package com.courses.spalah;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Ievgen Tararaka
 */
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface SampleAnnotation {
    // разрешены только примитивные типы и их массивы
    String value() default ""; // стринговый параметр с дефолтным значением

    int number(); // интовый параметр
}
