package cn.dx.diagnosis.parser.bean;

/**
 * 典型症状
 */
public class TypicalSymptom extends Symptom {

    public TypicalSymptom() {
    }

    public TypicalSymptom(Symptom symptom) {
        symptom.copy(this);
        this.weight *= 3;
    }
}
