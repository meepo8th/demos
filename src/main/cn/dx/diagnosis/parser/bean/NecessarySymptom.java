package cn.dx.diagnosis.parser.bean;

/**
 * 必备症状
 */
public class NecessarySymptom extends Symptom {

    public NecessarySymptom(Symptom symptom) {
        symptom.copy(this);
    }

    public NecessarySymptom() {

    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{\"NecessarySymptom\":{");
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
    public NecessarySymptom getNewInstance() {
        return new NecessarySymptom();
    }
}
