package leetcode.carfleet;

import java.util.ArrayList;
import java.util.List;

class Solution {
    public static void main(String[] args) {
        System.out.println(new Solution().carFleet(12, new int[]{10, 8, 0, 5, 3}, new int[]{2, 4, 1, 1, 3}));
    }

    public int carFleet(int target, int[] position, int[] speed) {
        List<Car> cars = new ArrayList<>();
        for (int i = 0; i < position.length; i++) {
            cars.add(new Car(position[i], speed[i]));
        }
        int time = 10 ^ 6;
        int fleet = 0;
        for (int i = 0; i < cars.size(); i++) {
            if ((target - cars.get(i).pos) < cars.get(i).speed * time) {
                fleet++;
                time = Math.min(time, (target - cars.get(i).pos) / cars.get(i).speed);
            } else {
                break;
            }
        }
        return fleet;
    }

    class Car implements Comparable<Car> {
        Integer pos;
        Integer speed;

        public Car(int pos, int speed) {
            this.pos = pos;
            this.speed = speed;
        }

        @Override
        public int compareTo(Car o) {
            return pos.compareTo(o.pos);
        }
    }
}