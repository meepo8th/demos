package demo;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class TestMapCousumer {
    static Consumer<Integer> consumerOne = i -> {
        System.out.println(i);
    };
    static Consumer<Integer> consumerTwo = i -> {
        System.out.println(i * 2);
    };
    static Consumer<Integer> consumerThree = i -> {
        System.out.println(i * 3);
    };

    public static void main(String[] args) {
        System.out.println(new Date(System.currentTimeMillis()));
        System.out.println(new Date());
        Map<Integer, Consumer<Integer>> consumerMap = new HashMap<>();
        consumerMap.put(1, consumerOne);
        consumerMap.put(2, consumerTwo);
        consumerMap.put(3, consumerThree);

        for (int i = 0; i < 1; i++) {
            for (Map.Entry<Integer, Consumer<Integer>> entry : consumerMap.entrySet()) {
                entry.getValue().accept(i);
            }
        }
    }
}
