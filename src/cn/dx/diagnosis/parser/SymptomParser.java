package cn.dx.diagnosis.parser;

import cn.dx.diagnosis.parser.bean.*;
import cn.dx.diagnosis.parser.transfer.*;
import cn.dx.diagnosis.parser.transfer.exception.BracketsException;
import cn.dx.diagnosis.parser.transfer.exception.TransferException;
import cn.dx.diagnosis.parser.transfer.inter.Transfer;
import org.apache.commons.lang.StringUtils;

import java.util.*;

public class SymptomParser {
    private SymptomParser() {

    }

    private static final List<Transfer> normalizationTransfer = new ArrayList<>();


    static {
        normalizationTransfer.add(new NormalizationReplaceTransfer());
        try {
            normalizationTransfer.add(new SquareBigBracketsTransfer());
            normalizationTransfer.add(new SubSpeciesBracketsTransfer());
        } catch (BracketsException e) {
            e.printStackTrace();
        }
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
        return TransferCode.decode(symptomNormalizationDesc).replaceAll("≮", ";")
                .replaceAll("≯", ";").replaceAll(";+", ";")
                .replaceAll("\\\n", "")
                .replaceAll(";([\\||&|∪])", "$1");
    }


    /**
     * 转换为症状
     *
     * @param symptomList
     * @return
     */
    public static List<Symptom> toSymptom(List<String> symptomList, String source, String disease) {
        List<Symptom> symptomTypeList = new ArrayList<>();
        for (String symptom : symptomList) {
            if (TransUtils.isValidSymptom(symptom)) {
                continue;
            }
            symptomTypeList.add(new Symptom(symptom, source, disease));
        }
        return symptomTypeList;
    }


    /**
     * 症状分类
     *
     * @param symptomList
     * @return
     */
    public static List<Symptom> typeSymptom(List<Symptom> symptomList) throws TransferException {
        List<Symptom> typedList = new ArrayList<>();
        for (int i = 0; i < symptomList.size(); i++) {
            typedList.addAll(typeSymptom(symptomList.get(i)));
        }
        return typedList;
    }

    /**
     * 症状分类
     *
     * @param symptom
     * @return
     */
    private static List<Symptom> typeSymptom(Symptom symptom) throws TransferException {
        List<Symptom> typedList = new ArrayList<>();
        String typeTypical = "￥";
        String confirmTypical = "★◎";
        String necessaryTypical = "!";
        if (TransUtils.isNotEmpty(symptom)) {
            if (typedSymptom(symptom.getContent())) {
                TypeDetection typeDetection = new TypeDetection(symptom.getContent(), symptom.getContent(), new String[]{"‡", "§", "-†", "†"}, new String[]{"‡", "§", "-†", "†"}).detection();
                String splitRegex = typeDetection.getSplitRegex();
                String splitChar = typeDetection.getSplitChar();
                String diseaseChild = symptom.getContent().split(splitRegex)[0];
                Symptom diseaseChildSymptom = new Symptom();
                symptom.copy(diseaseChildSymptom);
                diseaseChildSymptom.setDisease(diseaseChild + splitChar + diseaseChildSymptom.getDisease());
                addOneType(diseaseChildSymptom, typedList, typeTypical, confirmTypical, necessaryTypical);
            } else {
                addOneType(symptom, typedList, typeTypical, confirmTypical, necessaryTypical);
            }
        }
        return typedList;
    }

    /**
     * 是否是分类症状
     *
     * @param content
     * @return
     */
    private static boolean typedSymptom(String content) {
        return content.contains("§") || content.contains("‡") || content.contains("†");
    }

    /**
     * 增加一个症状分类
     *
     * @param symptom
     * @param typedList
     * @param typeTypical
     * @param confirmTypical
     * @param necessaryTypical
     * @throws TransferException
     */
    private static void addOneType(Symptom symptom, List<Symptom> typedList, String typeTypical, String confirmTypical, String necessaryTypical) throws TransferException {
        if (TransUtils.isNotEmpty(symptom)) {
            boolean confirmTyped = false;
            boolean typicalTyped = false;
            boolean necessaryTyped = false;
            for (int i = symptom.getContent().length() - 1; i >= 0; i--) {
                if (typeTypical.indexOf(symptom.getContent().charAt(i)) >= 0) {
                    typicalTyped = true;
                } else if (confirmTypical.indexOf(symptom.getContent().charAt(i)) >= 0) {
                    confirmTyped = true;
                    break;
                } else if (necessaryTypical.indexOf(symptom.getContent().charAt(i)) >= 0) {
                    necessaryTyped = true;
                }
            }
            if (necessaryTyped) {
                typedList.add(new NecessarySymptom(symptom));
            } else if (!typicalTyped && !confirmTyped) {
                typedList.add(new NormalSymptom(symptom));
            } else if (confirmTyped) {
                typedList.add(new ConfirmSymptom(symptom));
            } else {
                typedList.add(new TypicalSymptom(symptom));
            }
        }
    }


    /**
     * 分配概率
     *
     * @param symptomList
     * @return
     */
    private static Map<String, List<Symptom>> chanceSymptomWithType(List<Symptom> symptomList) {
        Map<String, List<Symptom>> changedSymptom = new HashMap<>();
        for (Symptom symptom : symptomList) {
            if (!changedSymptom.containsKey(symptom.getDisease())) {
                changedSymptom.put(symptom.getDisease(), new ArrayList<>());
            }
            changedSymptom.get(symptom.getDisease()).add(symptom);
        }
        String shortKey = getShortKey(changedSymptom.keySet());
        if (StringUtils.isNotBlank(shortKey)) {
            for (Map.Entry<String, List<Symptom>> entry : changedSymptom.entrySet()) {
                if (!shortKey.equals(entry.getKey())) {
                    for (Symptom cacheSymptom : changedSymptom.get(shortKey)) {
                        Symptom symptom = new Symptom();
                        cacheSymptom.copy(symptom);
                        symptom.setDisease(entry.getKey());
                        entry.getValue().add(symptom);
                    }

                }
            }
        }
        for (Map.Entry<String, List<Symptom>> entry : changedSymptom.entrySet()) {
            TransUtils.chanceOneTypeSymptom(entry.getValue(), entry.getKey());
        }
        return changedSymptom;
    }

    /**
     * 获取最短的key
     *
     * @param keys
     * @return
     */
    private static String getShortKey(Set<String> keys) {
        String shortKey = "";
        boolean containsTyped = false;
        for (String key : keys) {
            if (typedSymptom(key)) {
                containsTyped = true;
            } else if (StringUtils.isEmpty(shortKey) || key.length() <= shortKey.length()) {
                shortKey = key;
            }
        }
        return containsTyped ? shortKey : "";
    }

    /**
     * 分配概率
     *
     * @param symptomList
     * @return
     */
    private static List<Symptom> chanceSymptom(List<Symptom> symptomList) {
        List<Symptom> rtnList = new ArrayList<>();
        Map<String, List<Symptom>> mapType = chanceSymptomWithType(symptomList);
        for (Map.Entry<String, List<Symptom>> entry : mapType.entrySet()) {
            rtnList.addAll(entry.getValue());
        }
        return rtnList;
    }


    public static void main(String[] args) throws TransferException {
        String symptomDesc = symptomNormalization("[发热期§突起高热;*惊厥;食欲减退∪不安∪轻咳;@咽部@@扁桃体@充血;头颈部浅表淋巴结肿大;症状#体征≡轻微];[出疹期§￥高热骤降至正常c+出现皮疹;￥玫瑰红色p+压之褪色p+较少融合p+散在p+斑疹|斑丘疹;→躯干皮疹→颈∪上肢∪脸∪下肢≡皮疹→持续24～48h皮疹消退→色素沉着n+&脱皮n+→]\n");
        System.out.println(chanceSymptom(typeSymptom(toSymptom(TransUtils.splitStrWithBracket(symptomDesc), "症状", "小儿维生素A缺乏病"))));
    }
}
