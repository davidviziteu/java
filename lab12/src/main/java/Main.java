import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException {
        MyClassLoader myLoader1 = new MyClassLoader();
        myLoader1.loadClass(args[0]);


        System.out.println(Arrays.toString(Class.forName(args[0]).getMethods())
        );
        System.out.println(Arrays.toString(Class.forName(args[0]).getConstructors())
        );
        System.out.println(Arrays.toString(Class.forName(args[0]).getDeclaredFields())
        );

        int passed = 0, failed = 0;
        for (Method m : Class.forName(args[0]).getMethods()) {
            if (m.isAnnotationPresent(Test.class)) {
                try {
                    m.invoke(null);
                    passed++;
                } catch (Throwable ex) {
                    System.out.printf("Test %s failed: %s %n",
                            m, ex.getCause());
                    failed++;
                }
            }
        }
        System.out.printf("Passed: %d, Failed %d%n", passed, failed);
    }

}
