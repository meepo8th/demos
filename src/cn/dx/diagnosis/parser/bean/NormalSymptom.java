package cn.dx.diagnosis.parser.bean;

/**
 * 普通症状
 */
public class NormalSymptom extends Symptom {
    public NormalSymptom(Symptom symptom) {
        symptom.copy(this);
        this.weight = this.weight * getSourceWeight();
    }
}
