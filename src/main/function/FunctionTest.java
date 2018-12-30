package function;

import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 函数式编程测试
 *
 * @author Administrator
 */
public class FunctionTest {
    static List<Consumer<Integer>> testFunctions = new ArrayList<Consumer<Integer>>(5) {{
    }};

    static {
        testFunctions.add(e -> {
            System.out.println(e);
        });
        testFunctions.add(e -> {
            System.out.println(e * 2);
        });
        testFunctions.add(e -> {
            System.out.println(e * 3);
        });
        testFunctions.add(e -> {
            System.out.println(e * 4);
        });
        testFunctions.add(e -> {
            System.out.println(e * 5);
        });
    }

    public static void main(String[] args) {
        Consumer<Integer> consumer = e -> {
            System.out.println(e);
        };
        Collections.unmodifiableCollection(new ArrayList<>());
        Arrays.asList(new Integer[]{1, 2, 3, 4, 5, 6});
    }
}
