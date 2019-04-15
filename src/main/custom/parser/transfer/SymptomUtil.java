package custom.parser.transfer;

import custom.parser.bean.Symptom;
import custom.parser.transfer.exception.TransferException;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class SymptomUtil {
    private static CleanReplaceTransfer cleanReplaceTransfer = new CleanReplaceTransfer();

    private SymptomUtil() {

    }

    /**
     * 症状递归分割，直到不能分割为止
     *
     * @param symptom
     */
    public static void splitSymptom(Symptom symptom) throws TransferException {
        if (null != symptom && StringUtils.isNotBlank(symptom.getContent()) && StringUtils.isNotBlank(symptom.getParentContent())) {
            TypeDetection typeDetection = new TypeDetection(symptom.getContent(), symptom.getContent(), new String[]{"‡", "§", "-†", "†"}, new String[]{"‡", "§", "-†", "†"}).detection();
            String splitRegex = typeDetection.getSplitRegex();
            String diseaseChild = symptom.getContent().split(splitRegex)[0];
            symptom.setContent(symptom.getContent().replaceAll(diseaseChild, ""));
            symptom.setParentContent(symptom.getContent());
            symptom.setChildrenSymptom(splitSymptomOnce(symptom));
            if (null != symptom.getChildrenSymptom() && !symptom.getChildrenSymptom().isEmpty()) {
                for (Symptom childSymptom : symptom.getChildrenSymptom()) {
                    splitSymptom(childSymptom);
                }
            }
        }
    }

    /**
     * 一次分割并拆分概率
     *
     * @param symptom
     * @return
     */
    private static List<Symptom> splitSymptomOnce(Symptom symptom) {
        List<Symptom> rtnList = new ArrayList<>();
        String type = findSplitType(symptom.getContent());
        switch (type) {
            case "|":
                rtnList.addAll(splitLogic(symptom));
                break;
            case "→":
                rtnList.addAll(splitTimeLine(symptom));
                break;
            case ";":
                rtnList.addAll(splitParallel(symptom));
                break;
            default:
                break;
        }
        return rtnList;
    }

    /**
     * 并列分割
     *
     * @param symptom
     * @return
     */
    private static List<Symptom> splitParallel(Symptom symptom) {
        return null;
    }

    /**
     * 时间线分割
     *
     * @param symptom
     * @return
     */
    private static List<Symptom> splitTimeLine(Symptom symptom) {
        List<Symptom> rtnList = new ArrayList<>();
        List<String> strSplit = TransUtils.splitStrWithBracket(symptom.getContent(), '→');
        for (String str : strSplit) {
            if (TransUtils.isValidSymptom(str)) {
                Symptom cacheSymptom = symptom.getNewInstance();
                symptom.copy(cacheSymptom);
                symptom.setParentContent(symptom.getContent());
                rtnList.add(cacheSymptom);
            }
        }
        return rtnList;
    }

    /**
     * 逻辑关系分割
     *
     * @param symptom
     * @return
     */
    private static List<Symptom> splitLogic(Symptom symptom) {
        return null;
    }

    /**
     * 寻找最顶层的分类关系
     *
     * @param content
     * @return
     */
    private static String findSplitType(String content) {
        String type = "";

        return type;
    }


}
