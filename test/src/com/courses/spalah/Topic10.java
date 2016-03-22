package com.courses.spalah;

/**
 * @author Ievgen Tararaka
 */
public class Topic10 {
    public static void main(String[] args) {
    }

    public static class Pair<T> {
        private T left;
        private T right;

        public Pair() {
            this.left = null;
            this.right = null;
        }

        public T getLeft() {
            return left;
        }

        public T getRight() {
            return right;
        }

        public void setLeft(T left) {
            this.left = left;
        }

        public void setRight(T right) {
            this.right = right;
        }
    }

    public static class Pair2 extends Pair<String> {
    }

    public static class Manager extends Employee {
    }

    public static class Employee {
    }
}
