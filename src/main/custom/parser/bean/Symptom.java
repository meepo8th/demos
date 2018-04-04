package custom.parser.bean;

import custom.parser.transfer.SymptomUtil;
import custom.parser.transfer.exception.TransferException;

import java.io.Serializable;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

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
    protected String parentContent;
    List<Symptom> childrenSymptom;

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
     * 症状拆分并分配概率，一直拆分到不可拆分为止
     */
    public void splitSymptom() throws TransferException {
        SymptomUtil.splitSymptom(this);
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

    /**
     * 获取权重比例
     *
     * @return
     */
    protected Double getWeightRatio() throws TransferException {
        double ratio = 1d;
        String[] flags = new String[]{"ª", "º", "n+", "s+", "√"};
        double[] ratios = new double[]{1.1, 0.9, -10000, -0.8, -10000};
        if (flags.length != ratios.length) {
            throw new TransferException("权重比例配置错误：数量不一致");
        }
        return ratio;
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

    public List<Symptom> getChildrenSymptom() {
        return childrenSymptom;
    }

    public void setChildrenSymptom(List<Symptom> childrenSymptom) {
        this.childrenSymptom = childrenSymptom;
    }

    /**
     * 获取纯叶子节点,用堆栈实现递归防止递归层数过多导致系统堆栈不足
     *
     * @return
     */
    public List<Symptom> getPureLeafChild() {
        List<Symptom> allChild = new ArrayList<>();
        ArrayDeque<Symptom> stack = new ArrayDeque<>();
        if (isEmptyList(childrenSymptom)) {
            stack.push(this);
        }
        while (!stack.isEmpty()) {
            Symptom nowContent = stack.pop();
            if (null != nowContent) {
                if (isEmptyList(nowContent.childrenSymptom)) {
                    for (Symptom each : nowContent.childrenSymptom) {
                        stack.push(each);
                    }
                } else {
                    allChild.add(nowContent);
                }
            }
        }
        return allChild;
    }

    private boolean isEmptyList(List list) {
        return null != list && !list.isEmpty();
    }

    public String getParentContent() {
        return parentContent;
    }

    public void setParentContent(String parentContent) {
        this.parentContent = parentContent;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{\"Symptom\":{");
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
        sb.append(",\"parentContent\":\"")
                .append(parentContent).append('\"');
        sb.append(",\"childrenSymptom\":")
                .append(childrenSymptom);
        sb.append("}}");
        return sb.toString();
    }

    public Symptom getNewInstance() {
        return new Symptom();
    }
}
