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
            if (";".equals(symptom) || "\";".equals(symptom)) {
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
        if (null != symptom && StringUtils.isNotBlank(symptom.getContent())) {
            boolean confirmTyped = false;
            boolean typicalTyped = false;
            boolean necessaryTyped = false;
            for (int i = symptom.getContent().length() - 1; i >= 0 && !confirmTyped; i--) {
                if (typeTypical.indexOf(symptom.getContent().charAt(i)) >= 0) {
                    typicalTyped = true;
                } else if (confirmTypical.indexOf(symptom.getContent().charAt(i)) >= 0) {
                    confirmTyped = true;
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
        return typedList;
    }


    /**
     * 分配概率
     *
     * @param symptomList
     * @return
     */
    private static List<Symptom> chanceSymptom(List<Symptom> symptomList) {
        if (null != symptomList && !symptomList.isEmpty()) {
            double totalWeight = 0.00001d;
            for (Symptom symptom : symptomList) {
                if (symptom instanceof TypicalSymptom || symptom instanceof NormalSymptom) {
                    totalWeight += symptom.getWeight();
                }
            }
            double perWeight = 1.0 / totalWeight;
            for (Symptom symptom : symptomList) {
                if (symptom instanceof TypicalSymptom || symptom instanceof NormalSymptom) {
                    symptom.setChance(symptom.getWeight() * perWeight);
                }
            }
        }
        return symptomList;
    }


    public static void main(String[] args) throws TransferException {
        String symptomDesc = symptomNormalization("￥围生期窒息病史ª;宫内缺氧;胎盘功能异常;脐带≡脱垂|受压|绕颈;异常分娩;胎儿发育异常;呼吸暂停;严重e+肺部感染;心搏骤停;心动过缓;先天性心脏病,重度e+心力衰竭;大量e+失血;休克;颅内疾病:颅内出血;脑水肿;围生缺氧史;围生重度窒息史\n");
        System.out.println(chanceSymptom(typeSymptom(toSymptom(TransUtils.splitStrWithBracket(symptomDesc), "症状", "小儿维生素A缺乏病"))));
    }
}
