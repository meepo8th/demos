package cn.dx.diagnosis.parser.transfer;

import cn.dx.diagnosis.parser.bean.Brackets;
import cn.dx.diagnosis.parser.bean.Symptom;
import cn.dx.diagnosis.parser.transfer.exception.TransferException;
import org.apache.commons.lang.StringUtils;

import java.util.*;

public class SymptomUtil {
    private SymptomUtil() {

    }

    private static CleanReplaceTransfer cleanReplaceTransfer = new CleanReplaceTransfer();

    /**
     * 症状分割
     *
     * @param symptom
     */
    public static void splitSymptom(Symptom symptom) throws TransferException {
        if (null != symptom && StringUtils.isNotBlank(symptom.getContent())) {
            if (StringUtils.isNotBlank(symptom.getCleanContent())) {
                symptom.setCleanContent(cleanReplaceTransfer.trans(symptom.getContent()));
            }
        }
    }

    private static List<Symptom> splitSymptom(String content) {
        return null;
    }


}
