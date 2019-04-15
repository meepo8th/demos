package custom.parser.bean;

import custom.parser.transfer.exception.AtsException;
import custom.parser.transfer.exception.BracketsException;

import java.util.ArrayList;
import java.util.List;

/**
 * 括号类
 */
public class Ats {
    public static final String ATS_NOT_MATCH = "部位数量不匹配";
    private static char FLAG = '@';
    private int left = -1;//左括号位置
    private int right = -1;//右括号位置

    public Ats(int left, int right) {
        this.left = left;
        this.right = right;
    }

    public Ats() {
    }

    public Ats(int left) {
        this.left = left;
    }

    /**
     * 匹配@
     *
     * @param processStr
     * @return
     * @throws BracketsException
     */
    public static List<Ats> matchAts(String processStr) throws AtsException {
        List<Ats> ats = new ArrayList<>();
        for (int i = 0; i < processStr.length(); i++) {
            if (FLAG == processStr.charAt(i)) {
                if (ats.isEmpty() || ats.get(ats.size() - 1).getRight() > 0) {
                    ats.add(new Ats(i));
                } else {
                    ats.get(ats.size() - 1).setRight(i);
                }
            }
        }
        checkAts(ats);
        return ats;
    }

    /**
     * 检查是否匹配
     *
     * @param ats
     * @throws BracketsException
     */
    private static void checkAts(List<Ats> ats) throws AtsException {
        for (Ats at : ats) {
            if (!at.match()) {
                throw new AtsException(ATS_NOT_MATCH);
            }
        }
    }

    /**
     * 是否匹配
     *
     * @return
     */
    private boolean match() {
        return left >= 0 && right > 0;
    }

    public int getLeft() {
        return left;
    }

    public void setLeft(int left) {
        this.left = left;
    }

    public int getRight() {
        return right;
    }

    public void setRight(int right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "Brackets{" +
                "left=" + left +
                ", right=" + right +
                '}';
    }
}