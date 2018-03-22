package cn.dx.diagnosis.parser;

import cn.dx.diagnosis.parser.transfer.BigSpeciesBracketsTransfer;
import cn.dx.diagnosis.parser.transfer.ReplaceTransfer;
import cn.dx.diagnosis.parser.transfer.SquareBracketsTransfer;
import cn.dx.diagnosis.parser.transfer.SubSpeciesBracketsTransfer;
import cn.dx.diagnosis.parser.transfer.exception.BracketsException;
import cn.dx.diagnosis.parser.transfer.exception.TransferException;
import cn.dx.diagnosis.parser.transfer.inter.Transfer;

import java.util.*;

public class SymptomParser {
    private SymptomParser() {

    }

    private static final List<Transfer> normalizationTransfer = new ArrayList<>();
    private static final Map<Character, Character> leftOtherMap = new HashMap<>();
    private static final Map<Character, Character> rightOtherMap = new HashMap<>();

    static {
        normalizationTransfer.add(new ReplaceTransfer());
        try {
            normalizationTransfer.add(new SubSpeciesBracketsTransfer());
            normalizationTransfer.add(new SquareBracketsTransfer());
            normalizationTransfer.add(new BigSpeciesBracketsTransfer());
        } catch (BracketsException e) {
            e.printStackTrace();
        }
        leftOtherMap.put('{', '}');
        leftOtherMap.put('≮', '≯');
        rightOtherMap.put('}', '{');
        rightOtherMap.put('≯', '≮');
    }

    /**
     * 症状描述归一化
     *
     * @param symptomDesc
     * @return
     */
    public static String symptomNormalization(String symptomDesc) throws TransferException {
        String symptomNormalizationDesc = symptomDesc;
        for (Transfer transfer : normalizationTransfer) {
            if (transfer.canTrans(symptomNormalizationDesc)) {
                symptomNormalizationDesc = transfer.trans(symptomNormalizationDesc);
            }
        }
        return symptomNormalizationDesc;
    }

    /**
     * 症状拆分
     *
     * @param symptomDesc
     * @return
     */
    public static List<String> symptomSplit(String symptomDesc) {
        List<String> symptomSplit = new ArrayList<>();
        Character flag = ' ';
        Map<Character, Deque> inlineFlagMap = new HashMap<>();
        inlineFlagMap.put('→', new ArrayDeque<Character>());
        inlineFlagMap.put('{', new ArrayDeque<Character>());
        inlineFlagMap.put('≮', new ArrayDeque<Character>());
        inlineFlagMap.put('}', new ArrayDeque<Character>());
        inlineFlagMap.put('≯', new ArrayDeque<Character>());
        int lastPos = 0;
        for (int i = 0; i < symptomDesc.length(); i++) {
            if (inlineFlagMap.containsKey(symptomDesc.charAt(i))) {
                lastPos = mergeFlagMap(symptomDesc, symptomSplit, flag, inlineFlagMap, lastPos, i);
            }
            if (';' == symptomDesc.charAt(i) && inlineFlagMapMatch(inlineFlagMap)) {
                addSplit(symptomDesc, symptomSplit, lastPos, i);
                lastPos = i + 1;
            }
        }
        return symptomSplit;
    }

    /**
     * 开始结束标志闭环
     *
     * @param symptomDesc
     * @param symptomSplit
     * @param flag
     * @param inlineFlagMap
     * @param lastPos
     * @param i
     * @return
     */
    private static int mergeFlagMap(String symptomDesc, List<String> symptomSplit, Character flag, Map<Character, Deque> inlineFlagMap, int lastPos, int i) {
        System.out.println(symptomDesc.charAt(i));
        if (leftOtherMap.containsKey(symptomDesc.charAt(i))) {
            inlineFlagMap.get(symptomDesc.charAt(i)).push(flag);
        } else if (rightOtherMap.containsKey(symptomDesc.charAt(i))) {
            inlineFlagMap.get(rightOtherMap.get(symptomDesc.charAt(i))).pop();
            if (inlineFlagMap.get(symptomDesc.charAt(i)).isEmpty()) {
                addSplit(symptomDesc, symptomSplit, lastPos, i);
                lastPos = i + 1;
            }
        } else if ('→' == symptomDesc.charAt(i)) {
            if (i == 0 || ";{≮".indexOf(symptomDesc.charAt(i - 1)) >= 0) {
                inlineFlagMap.get(symptomDesc.charAt(i)).push(flag);
            } else if (i == symptomDesc.length() - 1 || ";}≯".indexOf(symptomDesc.charAt(i + 1)) >= 0) {
                inlineFlagMap.get(symptomDesc.charAt(i)).pop();
                addSplit(symptomDesc, symptomSplit, lastPos, i);
                lastPos = i + 1;
            }
        }
        return lastPos;
    }

    /**
     * 添加拆分内容
     *
     * @param symptomDesc
     * @param symptomSplit
     * @param start
     * @param end
     */
    private static void addSplit(String symptomDesc, List<String> symptomSplit, int start, int end) {
        if (end > start) {
            symptomSplit.add(symptomDesc.substring(start, end + 1));
        }
    }

    /**
     * 校验是否在匹配中
     *
     * @param inlineFlagMap
     * @return
     */
    private static boolean inlineFlagMapMatch(Map<Character, Deque> inlineFlagMap) {
        if (null != inlineFlagMap) {
            for (Map.Entry<Character, Deque> entry : inlineFlagMap.entrySet()) {
                if (!entry.getValue().isEmpty()) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) throws TransferException {
        String symptomDesc = symptomNormalization("￥围生期窒息病史ª;宫内缺氧;胎盘功能异常;脐带≡脱垂|受压|绕颈;异常分娩;胎儿发育异常;呼吸暂停;严重e+肺部感染;心搏骤停;心动过缓;先天性心脏病,重度e+心力衰竭;大量e+失血;休克;颅内疾病:颅内出血;脑水肿;围生缺氧史;围生重度窒息史\n");
        System.out.println(symptomDesc);
        System.out.println(symptomSplit(symptomDesc));
    }
}
