package funny;

import java.util.Arrays;
import java.util.Random;

/**
 * @author admin
 */
public class OnlyFunny {
    public static void main(String[] args) {
        int[] array = new int[10000000];
        for (int i = 0; i < 3; i++) {
            sortAndPrint(array);
        }
    }

    private static void sortAndPrint(int[] array) {
        randomArray(array);
//        System.out.println(Arrays.toString(array));
        long l1 = System.currentTimeMillis();
        quickSort(array);
        System.out.format("use:%d\r\n", System.currentTimeMillis() - l1);
//        System.out.println(Arrays.toString(array));

    }

    private static void randomArray(int[] array) {
        Random random = new Random();
        for (int i = 0; i < array.length; i++) {
            array[i] = random.nextInt(10000);
        }
    }

    public static void num123() {
        for (int i = 123; i <= 987 / 3; i++) {
            if (num1to9(i, i * 2, i * 3)) {
                System.out.format("%d,%d,%d\r\n", i, i * 2, i * 3);
            }
        }
    }

    private static boolean num1to9(int i, int i1, int i2) {
        char[] cache = new char[9];

        setCache(cache, i);
        setCache(cache, i1);
        setCache(cache, i2);
        for (int c : cache) {
            if (0 == c) {
                return false;
            }
        }
        return true;
    }

    private static void setCache(char[] cache, int i) {
        set1Cache(i / 100, cache);
        set1Cache(i % 100 / 10, cache);
        set1Cache(i % 10, cache);
    }

    private static void set1Cache(int i, char[] cache) {
        if (i > 0 && i <= cache.length) {
            cache[i - 1] = 1;
        }
    }

    /**
     * 快速排序
     *
     * @param array
     */
    public static void quickSort(int[] array) {
        quickSort(array, 0, array.length - 1);
    }

    /**
     * 快速排序，添加随机因子，使其更稳定
     *
     * @param array
     * @param start
     * @param end
     */
    public static void quickSort(int[] array, int start, int end) {
        if (start >= end) {
            return;
        }
        int tmp;
        if (end - start > 15) {
            int newStart = start + new Random().nextInt(end - start);
            tmp = array[newStart];
            array[newStart] = array[start];
            array[start] = tmp;
        }
        int base = array[start];
        int i = start, j = end;
        while (i != j) {
            while (array[j] >= base && j > i) {
                j--;
            }
            while (array[i] <= base && j > i) {
                i++;
            }
            if (i < j) {
                tmp = array[i];
                array[i] = array[j];
                array[j] = tmp;
            }
        }
        array[start] = array[i];
        array[i] = base;
        quickSort(array, start, i - 1);
        quickSort(array, i + 1, end);
    }

    /**
     * 选择排序
     *
     * @param array
     */
    public static void selectSort(int[] array) {
        int tmp;
        for (int i = 0; i < array.length; i++) {
            for (int j = i; j < array.length; j++) {
                if (array[j] < array[i]) {
                    tmp = array[i];
                    array[i] = array[j];
                    array[j] = tmp;
                }
            }
        }
    }

    /**
     * 冒泡排序
     *
     * @param array
     */
    public static void bubbleSort(int[] array) {
        int tmp;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    tmp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = tmp;
                }
            }
        }
    }
}
