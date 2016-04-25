package com.courses.spalah;

/**
 * @author Ievgen Tararaka
 */
public class ImmutableEntity {
    private int value;

    public ImmutableEntity(int value) {
        this.value = value;
    }

    public int getValue() { // есть геттер, но нет сеттера, таким образом нельзя изменить значение value
        return value;
    }
}
