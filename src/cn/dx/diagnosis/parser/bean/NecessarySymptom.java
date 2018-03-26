package cn.dx.diagnosis.parser.bean;

/**
 * 必备症状
 */
public class NecessarySymptom extends Symptom {

    public NecessarySymptom(Symptom symptom) {
        symptom.copy(this);
    }

    @Override
    public String toString() {
        return "NecessarySymptom{" +
                "content='" + content + '\'' +
                ", source='" + source + '\'' +
                ", disease='" + disease + '\'' +
                ", weight=" + weight +
                ", chance=" + chance +
                ", cleanContent='" + cleanContent + '\'' +
                '}';
    }
}
