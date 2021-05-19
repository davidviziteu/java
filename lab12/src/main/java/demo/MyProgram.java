package demo;

import org.junit.jupiter.api.Test;

public class MyProgram {
    @Test
    public static void m1() {
    }

    public static void m2() {
    }

    @Test
    public static void m3() {
        throw new RuntimeException("Boom");
    }

    public static void m4() {
    }

    @Test
    public static void m5() {
    }

    public static void m6() {
    }

    @Test
    public static void m7() {
        throw new RuntimeException("Crash");
    }

    @Test
    public static void m8(int arg1) {
        throw new RuntimeException("Crash args");
    }

    @Test
    private static void m9(int arg12) {
        throw new RuntimeException("Crash args private");
    }
}
