package practice;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class JSetZeroes {

    public static void main(String[] args) {
        int[][] matrix = {{1, 2, 3}, {4, 0, 5}, {6, 7, 8}};
        setZeroes(matrix);

    }

    public static void setZeroes(int[][] matrix) {
        HashSet<Integer> row = new HashSet();
        HashSet<Integer> col = new HashSet();

        if (matrix.length == 0) {
            return;
        }
        //No.1
        //开始写代码，请在这里写入你实现问题的代码
        List<int[]> list = new ArrayList();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] == 0) {
                    list.add(new int[]{i, j});
                }
            }
        }

        for (int[] array : list) {
            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix[i].length; j++) {
                    if (i == array[0] || j == array[1]) {
                        matrix[i][j] = 0;
                    }
                }
            }
        }


        //end_code
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j]);
            }
            System.out.println();
        }

    }
}