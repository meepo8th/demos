public class Line {
    public static String Tickets(int[] peopleInLine) {
        //Your code is here...
        int[] base = new int[5];
        for (int in : peopleInLine) {
            switch (in) {
                case 25:
                    base[1]++;
                    break;
                case 50:
                    if (base[1] > 0) {
                        base[1]--;
                        base[2]++;
                    } else {
                        return "NO";

                    }
                    break;
                case 100:
                    if (base[2] > 0 && base[1] > 0) {
                        base[2]--;
                        base[1]--;
                        base[4]++;
                    } else if (base[1] >= 3) {
                        base[1] -= 3;
                        base[4]++;
                    } else {
                        return "NO";
                    }
                    break;

            }
        }
        return "YES";
    }

    public static void main(String args[]) {
        System.out.println(Tickets(new int[]{25, 25, 50, 25, 100}));
    }
}