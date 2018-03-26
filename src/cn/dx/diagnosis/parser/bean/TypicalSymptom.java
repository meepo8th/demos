package cn.dx.diagnosis.parser.bean;

import cn.dx.diagnosis.parser.transfer.exception.TransferException;

/**
 * 典型症状
 */
public class TypicalSymptom extends Symptom {

    public TypicalSymptom() {
    }

    public TypicalSymptom(Symptom symptom) throws TransferException {
        symptom.copy(this);
        //典型症状不根据来源算权重
        this.weight = this.weight * 3 * getWeightRatio();
    }

    @Override
    public String toString() {
        return "TypicalSymptom{" +
                "content='" + content + '\'' +
                ", source='" + source + '\'' +
                ", disease='" + disease + '\'' +
                ", weight=" + weight +
                ", chance=" + chance +
                ", cleanContent='" + cleanContent + '\'' +
                '}';
    }
}
