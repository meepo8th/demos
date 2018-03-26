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
        final StringBuilder sb = new StringBuilder("{\"NormalSymptom\":{");
        sb.append("\"content\":\"")
                .append(content).append('\"');
        sb.append(",\"source\":\"")
                .append(source).append('\"');
        sb.append(",\"disease\":\"")
                .append(disease).append('\"');
        sb.append(",\"weight\":")
                .append(weight);
        sb.append(",\"chance\":")
                .append(chance);
        sb.append(",\"cleanContent\":\"")
                .append(cleanContent).append('\"');
        sb.append(",\"childrenSymptom\":")
                .append(childrenSymptom);
        sb.append("}}");
        return sb.toString();
    }
}
