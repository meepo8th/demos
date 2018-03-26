package cn.dx.diagnosis.parser.bean;

import cn.dx.diagnosis.parser.transfer.exception.TransferException;

/**
 * 普通症状
 */
public class NormalSymptom extends Symptom {
    public NormalSymptom(Symptom symptom) throws TransferException {
        symptom.copy(this);
        //可能根据source计算权重，目前是一致的
        this.weight = this.weight * getSourceWeight()*getWeightRatio();
    }

    @Override
    public String toString() {
        return "NormalSymptom{" +
                "content='" + content + '\'' +
                ", source='" + source + '\'' +
                ", disease='" + disease + '\'' +
                ", weight=" + weight +
                ", chance=" + chance +
                ", cleanContent='" + cleanContent + '\'' +
                '}';
    }
}
