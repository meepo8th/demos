package test;

import java.util.Arrays;
import java.util.Random;

public class Test {
    public static void setRandom100() {
        int[] array = new int[100];
        for (int i = 0; i < 100; i++) {
            array[i] = i + 1;
        }
        for (int i = 0; i < 100; i++) {
            int j = new Random().nextInt(100);
            int tmp = array[i];
            array[i] = array[j];
            array[j] = tmp;
        }
        System.out.println(Arrays.toString(array));
    }

    public static void main(String[] args) {
        System.out.println(3.3 - 3);
        System.out.println(3 == 3.0);
        setRandom100();
    }
}
