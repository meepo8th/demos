package leetcode.minRefuelStops;

class Solution {
    public int minRefuelStops(int target, int startFuel, int[][] stations) {
        Car[][] travel = new Car[stations.length][stations.length];
        for (int j = 0; j < stations.length; j++) {
            for (int i = 0; i < stations.length; i++) {
                if (i == 0 || j == 0) {
                    travel[i][j] = new Car(startFuel, 0);
                }
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().minRefuelStops(1, 1, new int[][]{}));
    }
}

class Car {
    int fuel;
    int step;

    public Car(int fuel, int step) {
        this.fuel = fuel;
        this.step = step;
    }
}