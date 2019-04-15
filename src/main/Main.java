import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String args[]) throws IOException {
        List<Unit> units = new ArrayList<>();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int unitCounts = Integer.parseInt(br.readLine());
        for (int i = 0; i < unitCounts; i++) {
            units.add(new Unit(br.readLine()));
        }
        int m = Integer.parseInt(br.readLine());
        int allMana = 0;
        for (int i = 0; i < m; i++) {
            allMana += mOperator(units, br.readLine());
        }
        System.out.println(allMana);
    }

    private static int mOperator(List<Unit> units, String s) {
        String[] flags = s.split(" ");
        return mOperator(units, Integer.parseInt(flags[0]), Integer.parseInt(flags[1]), Integer.parseInt(flags[2]));
    }

    private static int mOperator(List<Unit> units, int t, int l, int r) {
        int mana = 0;
        for (int i = l - 1; i < r; i++) {
            mana += (units.get(i).getMana(t));
        }
        return mana;
    }

    static class Unit {
        int nowMana;
        int maxMana;
        int rate;
        int lastT;

        public Unit(int nowMana, int maxMana, int rate) {
            init(nowMana, maxMana, rate);

        }

        public Unit(String input) {
            String[] flags = input.split(" ");
            init(Integer.parseInt(flags[0]), Integer.parseInt(flags[1]), Integer.parseInt(flags[2]));
        }

        private void init(int nowMana, int maxMana, int rate) {
            this.nowMana = nowMana;
            this.maxMana = maxMana;
            this.rate = rate;
            this.lastT = 0;
        }

        public int getMana(int t) {
            int mana = Math.min(nowMana + (t - lastT) * rate, maxMana);
            lastT = t;
            nowMana = 0;
            return mana;
        }
    }
}