package custom.parser.transfer;

import custom.parser.bean.NormalSymptom;
import custom.parser.bean.Symptom;
import custom.parser.bean.TypicalSymptom;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TransUtils {

    private static final Map<Character, Character> leftOtherMap = new HashMap<>();
    private static final Map<Character, Character> rightOtherMap = new HashMap<>();


    static {
        leftOtherMap.put('{', '}');
        rightOtherMap.put('}', '{');
        leftOtherMap.put('【', '】');
        rightOtherMap.put('】', '【');
    }

    /**
     * 症状拆分
     *
     * @param symptomDesc
     * @return
     */
    public static List<String> splitStrWithBracket(String symptomDesc) {
        return splitStrWithBracket(symptomDesc, ';');
    }

    /**
     * 症状拆分
     *
     * @param symptomDesc
     * @return
     */
    public static List<String> splitStrWithBracket(String symptomDesc, Character splitChar) {
        List<String> symptomSplit = new ArrayList<>();
        Map<Character, Integer> inlineFlagMap = new HashMap<>();
        inlineFlagMap.put('→', 0);
        inlineFlagMap.put('【', 0);
        inlineFlagMap.put('】', 0);
        inlineFlagMap.put('{', 0);
        inlineFlagMap.put('}', 0);
        int lastPos = 0;
        for (int i = 0; i < symptomDesc.length(); i++) {
            if (inlineFlagMap.containsKey(symptomDesc.charAt(i))) {
                lastPos = endFlagMerge(symptomDesc, inlineFlagMap, lastPos, i);
            }
            if (splitChar == symptomDesc.charAt(i) && inlineFlagMapMatch(inlineFlagMap)) {
                addSplit(symptomDesc, symptomSplit, lastPos, i);
                lastPos = i + 1;
            }
            if (i == symptomDesc.length() - 1) {
                addSplit(symptomDesc, symptomSplit, lastPos, i);
            }
        }
        return symptomSplit;
    }

    /**
     * 开始结束标志闭环
     *
     * @param symptomDesc
     * @param inlineFlagMap
     * @param lastPos
     * @param i
     * @return
     */
    private static int endFlagMerge(String symptomDesc, Map<Character, Integer> inlineFlagMap, int lastPos, int i) {
        if (leftOtherMap.containsKey(symptomDesc.charAt(i))) {
            inlineFlagMap.put(symptomDesc.charAt(i), inlineFlagMap.get(symptomDesc.charAt(i)) + 1);
        } else if (rightOtherMap.containsKey(symptomDesc.charAt(i))) {
            inlineFlagMap.put(rightOtherMap.get(symptomDesc.charAt(i)), inlineFlagMap.get(rightOtherMap.get(symptomDesc.charAt(i))) - 1);
        } else if ('→' == symptomDesc.charAt(i)) {
            if (i == 0 || ";{".indexOf(symptomDesc.charAt(i - 1)) >= 0) {
                inlineFlagMap.put(symptomDesc.charAt(i), inlineFlagMap.get(symptomDesc.charAt(i)) + 1);
            } else if (i == symptomDesc.length() - 1 || ";}≯".indexOf(symptomDesc.charAt(i + 1)) >= 0) {
                inlineFlagMap.put(symptomDesc.charAt(i), inlineFlagMap.get(symptomDesc.charAt(i)) - 1);
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
        if (end >= start) {
            symptomSplit.add(symptomDesc.substring(start, end + 1));
        }
    }

    /**
     * 校验是否在匹配中
     *
     * @param inlineFlagMap
     * @return
     */
    private static boolean inlineFlagMapMatch(Map<Character, Integer> inlineFlagMap) {
        if (null != inlineFlagMap) {
            for (Map.Entry<Character, Integer> entry : inlineFlagMap.entrySet()) {
                if (entry.getValue() > 0) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * 判断是否是空症状
     *
     * @param symptom
     * @return
     */
    public static boolean isNotEmpty(Symptom symptom) {
        return null != symptom && StringUtils.isNotBlank(symptom.getContent()) && symptom.getContent().length() > 1;
    }

    /**
     * 是否是无效症状
     *
     * @param symptom
     * @return
     */
    public static boolean isValidSymptom(String symptom) {
        if (StringUtils.isNotBlank(symptom)) {
            String validSymptom = "";
            validSymptom = symptom.replaceAll(";|:|\"", "");
            if (StringUtils.isNotBlank(validSymptom)) {
                return (validSymptom.replaceAll("^.*(:|‡|§|†)", "")).length() < 2;
            }
        }
        return true;
    }

    /**
     * 分配同一类概率
     *
     * @param symptomList
     * @param key
     * @return
     */
    public static List<Symptom> chanceOneTypeSymptom(List<Symptom> symptomList, String key) {
        return chanceOneTypeSymptom(symptomList, key, 1);
    }

    /**
     * 分配同一类概率
     *
     * @param symptomList
     * @param key
     * @return
     */
    public static List<Symptom> chanceOneTypeSymptom(List<Symptom> symptomList, String key, double allChange) {
        if (null != symptomList && !symptomList.isEmpty()) {
            double totalWeight = 0.00001d;
            for (Symptom symptom : symptomList) {
                if (symptom instanceof TypicalSymptom || symptom instanceof NormalSymptom) {
                    if (symptom.getWeight() >= 0) {
                        totalWeight += symptom.getWeight();
                    }
                }
                symptom.setDisease(key);
            }
            double perWeight = allChange / totalWeight;
            for (Symptom symptom : symptomList) {
                if (symptom instanceof TypicalSymptom || symptom instanceof NormalSymptom) {
                    symptom.setChance(symptom.getWeight() * perWeight);
                }
            }
        }
        return symptomList;
    }
}
