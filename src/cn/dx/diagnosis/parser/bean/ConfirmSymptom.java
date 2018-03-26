package cn.dx.diagnosis.parser.bean;

/**
 * 确诊症状
 */
public class ConfirmSymptom extends Symptom {

    public ConfirmSymptom(Symptom symptom) {
        symptom.copy(this);
    }

    @Override
    public String toString() {
        return "ConfirmSymptom{" +
                "content='" + content + '\'' +
                ", source='" + source + '\'' +
                ", disease='" + disease + '\'' +
                ", weight=" + weight +
                ", chance=" + chance +
                ", cleanContent='" + cleanContent + '\'' +
                '}';
    }
}
