package com.courses.spalah;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.sql.SQLException;
import java.util.Arrays;

/**
 * @author Ievgen Tararaka
 */
public class Topic6 {
    public static void test() throws Exception {
        // 1) Classic Error
        int[] array = {1, 2, 3, 4, 5};
        System.out.println(Arrays.toString(reverse(array)));

        // 2) All types
        exceptionTypes();

        // 3) Checked
        readFromConsole();

        // 4) Unchecked
        compareString();

        // 5) Several exceptions in method signature
        manyExceptions();

        // 6) throws on overriding
        TestA a = new TestA();

        // 7) throws of subtypes
        subException();

        // 8) Exception message
        exceptionMessage("b");

        // 9) try-catch
        tryCatch();

        // 10) Multi catch, rethrow
        multiCatchAndReThrow();

        // 11) exception.getClause()
        getClause();

        // 12) exception.getStackTrace( )
        stackTrace();

        // ) try-catch-finally
        tryCatchFinally();

        // ) try-finally
        tryFinally();
    }

    // Классический случай
    public static int[] reverse(int[] array) {
        for (int i = 0; i < array.length / 2; i++) {
            int tmp = array[i];
            array[i] = array[array.length - i];
            array[array.length - i] = tmp;
        }
        return array;
    }

    public static void exceptionTypes() {
//        throw new Error();
//        throw new Exception();
//        throw new RuntimeException();
    }

    public static String[] readFromConsole() throws IOException {
        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
        String input = bufferRead.readLine();
        return input.split("\\s");
    }

    public static void compareString() {
        String s = null;
        s.equals("a");
    }

    public static void manyExceptions() throws IOException, SQLException, ArrayIndexOutOfBoundsException, MalformedURLException {
    }

    public static void subException() throws IOException{
        throw new SubException();
    }

    public static void exceptionMessage(String a) {
        if (!a.equals("a")) {
            throw new IllegalArgumentException("a is expected here !!!");
        }
    }

    public static void tryCatch() {

    }

    public static void multiCatchAndReThrow() {

    }

    public static void getClause() {

    }

    public static void stackTrace() {

    }

    public static void tryCatchFinally() {

    }

    public static void tryFinally() {

    }

    public static class SubException extends IOException {}

    public static class TestA {
        public void a() {
        }
    }

//    public static class TestB extends TestA {
//        public void a() throws IOException, SQLException {
//        }
//    }

//    public static class TestC extends TestB {
//        @Override
//        public void a() throws IOException {
//        }
//    }
}
