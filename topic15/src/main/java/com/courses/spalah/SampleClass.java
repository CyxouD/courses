package com.courses.spalah;

/**
 * @author Ievgen Tararaka
 */
@SampleAnnotation(value = "1", number = 1)
public class SampleClass {
    public static char static_char;

    public long publicLong;
    int defaultInt;
    protected String protectedString;
    private boolean privateBoolean;

    public static SampleClass newInstance() {
        return new SampleClass();
    }

    public SampleClass() {
    }

    @SuppressWarnings("unchecked") // аннотации могут иметь аргументы
    public SampleClass(long publicLong, int defaultInt, String protectedString, boolean privateBoolean) {
        this.publicLong = publicLong;
        this.defaultInt = defaultInt;
        this.protectedString = protectedString;
        this.privateBoolean = privateBoolean;
    }

    @Deprecated // показывает, что метод не стоит больше использовать
    private boolean isPrivateBoolean() {
        return privateBoolean;
    }

    public void setPrivateBoolean(boolean privateBoolean) {
        this.privateBoolean = privateBoolean;
    }

    @Override // говорит о том, что метод был реализован наследником
    public String toString() {
        return "";
    }
}
