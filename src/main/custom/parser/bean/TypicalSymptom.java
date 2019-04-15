package custom.parser.bean;

import custom.parser.transfer.exception.TransferException;

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
        final StringBuilder sb = new StringBuilder("{\"TypicalSymptom\":{");
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

    @Override
    public TypicalSymptom getNewInstance() {
        return new TypicalSymptom();
    }
}
