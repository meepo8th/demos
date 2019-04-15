package leetcode.computearea;

class Solution {
    public static void main(String[] args) {
        System.out.println(new Solution().computeArea(-3, 0, 3, 4, 0, -1, 9, 2));
    }

    public int computeArea(int A, int B, int C, int D, int E, int F, int G, int H) {
        return computeArea(new Rect(A, B, C, D), new Rect(E, F, G, H));
    }

    private int computeArea(Rect rect0, Rect rect1) {
        return rect0.area() + rect1.area() - rect0.intersectArea(rect1);
    }

    class Rect {
        int x1;
        int x2;
        int y1;
        int y2;

        public Rect(int x1, int y1, int x2, int y2) {
            this.x1 = x1;
            this.x2 = x2;
            this.y1 = y1;
            this.y2 = y2;
        }

        public int area() {
            return Math.abs((x2 - x1) * (y2 - y1));
        }

        public int intersectArea(Rect rect1) {
            int maxX = Math.max(x2, rect1.x2);
            int minX = Math.min(x1, rect1.x1);
            int maxY = Math.max(y2, rect1.y2);
            int minY = Math.min(y1, rect1.y1);
            int area = (maxX - minX) * (maxY - minY);
            return area > 0 ? area : 0;
        }
    }
}