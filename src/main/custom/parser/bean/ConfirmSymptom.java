package custom.parser.bean;

/**
 * 确诊症状
 */
public class ConfirmSymptom extends Symptom {

    public ConfirmSymptom(Symptom symptom) {
        symptom.copy(this);
    }

    public ConfirmSymptom() {
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{\"ConfirmSymptom\":{");
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
    public ConfirmSymptom getNewInstance() {
        return new ConfirmSymptom();
    }
}
