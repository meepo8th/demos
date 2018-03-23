package cn.dx.diagnosis.parser.bean;

import java.io.Serializable;

/**
 * 症状
 */
public class Symptom implements Serializable {
    protected String content;
    protected String source;
    protected String disease;
    protected Double weight = 1d;
    protected Double chance;
    protected String cleanContent;

    public Symptom() {
    }

    public Symptom(String content, String source, String disease) {
        this.content = content;
        this.source = source;
        this.disease = disease;
    }

    public Symptom copy(Symptom symptom) {
        if (null == symptom) {
            symptom = new Symptom();
        }
        symptom.content = this.content;
        symptom.source = this.source;
        symptom.disease = this.disease;
        return symptom;
    }

    /**
     * 根据resource获取权重
     *
     * @return
     */
    protected Double getSourceWeight() {
        if ("流行病学".equals(source)) {
            return 0.9;
        } else {
            return 1d;
        }
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getCleanContent() {
        return cleanContent;
    }

    public void setCleanContent(String cleanContent) {
        this.cleanContent = cleanContent;
    }

    public String getDisease() {
        return disease;
    }

    public void setDisease(String disease) {
        this.disease = disease;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Double getChance() {
        return chance;
    }

    public void setChance(Double chance) {
        this.chance = chance;
    }

    @Override
    public String toString() {
        return "Symptom{" +
                "content='" + content + '\'' +
                ", source='" + source + '\'' +
                ", disease='" + disease + '\'' +
                ", weight=" + weight +
                ", chance=" + chance +
                ", cleanContent='" + cleanContent + '\'' +
                '}';
    }
}
