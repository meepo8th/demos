package cn.dx.diagnosis.parser;

import cn.dx.diagnosis.parser.bean.ConfirmSymptom;
import cn.dx.diagnosis.parser.bean.NormalSymptom;
import cn.dx.diagnosis.parser.bean.Symptom;
import cn.dx.diagnosis.parser.bean.TypicalSymptom;
import cn.dx.diagnosis.parser.transfer.*;
import cn.dx.diagnosis.parser.transfer.exception.BracketsException;
import cn.dx.diagnosis.parser.transfer.exception.TransferException;
import cn.dx.diagnosis.parser.transfer.inter.Transfer;
import org.apache.commons.lang.StringUtils;

import java.util.*;

public class SymptomParser {
    private SymptomParser() {

    }

    private static final List<Transfer> normalizationTransfer = new ArrayList<>();


    static {
        normalizationTransfer.add(new ReplaceTransfer());
        try {
            normalizationTransfer.add(new SquareBigBracketsTransfer());
            normalizationTransfer.add(new SubSpeciesBracketsTransfer());
        } catch (BracketsException e) {
            e.printStackTrace();
        }
    }

    /**
     * 症状描述归一化
     *
     * @param symptomDesc
     * @return
     */
    public static String symptomNormalization(String symptomDesc) throws TransferException {
        String symptomNormalizationDesc = symptomDesc;
        for (Transfer transfer : normalizationTransfer) {
            if (transfer.canTrans(symptomNormalizationDesc)) {
                symptomNormalizationDesc = transfer.trans(symptomNormalizationDesc);
            }
        }
        return TransferCode.decode(symptomNormalizationDesc).replaceAll("≮", ";")
                .replaceAll("≯", ";").replaceAll(";+", ";")
                .replaceAll("\\\n", "")
                .replaceAll(";([\\||&|∪])", "$1");
    }


    /**
     * 转换为症状
     *
     * @param symptomList
     * @return
     */
    public static List<Symptom> toSymptom(List<String> symptomList, String source, String disease) {
        List<Symptom> symptomTypeList = new ArrayList<>();
        for (String symptom : symptomList) {
            if (";".equals(symptom) || "\";".equals(symptom)) {
                continue;
            }
            symptomTypeList.add(new Symptom(symptom, source, disease));
        }
        return symptomTypeList;
    }

    /**
     * 症状分类
     *
     * @param symptomList
     * @return
     */
    public static List<Symptom> typeSymptom(List<Symptom> symptomList) {
        List<Symptom> typedList = new ArrayList<>();
        for (int i = 0; i < symptomList.size(); i++) {
            typedList.addAll(typeSymptom(symptomList.get(i)));
        }
        return typedList;
    }

    /**
     * 症状分类
     *
     * @param symptom
     * @return
     */
    private static List<Symptom> typeSymptom(Symptom symptom) {
        List<Symptom> typedList = new ArrayList<>();
        String typeTypical = "￥";
        String confirmTypical = "★◎";
        if (null != symptom && StringUtils.isNotBlank(symptom.getContent())) {
            boolean typed = false;
            for (int i = 0; i < symptom.getContent().length() && !typed; i++) {
                if (typeTypical.indexOf(symptom.getContent().charAt(i)) >= 0) {
                    typed = true;
                    typedList.add(new TypicalSymptom(symptom));
                } else if (confirmTypical.indexOf(symptom.getContent().charAt(i)) >= 0) {
                    typedList.add(new ConfirmSymptom(symptom));
                    typed = true;
                }
            }
            if (!typed) {
                typedList.add(new NormalSymptom(symptom));
            }
        }
        return typedList;
    }


    /**
     * 分配概率
     *
     * @param symptomList
     * @return
     */
    private static List<Symptom> chanceSymptom(List<Symptom> symptomList) {
        if (null != symptomList && !symptomList.isEmpty()) {
            double totalWeight = 0;
            for (Symptom symptom : symptomList) {
                if (symptom instanceof TypicalSymptom || symptom instanceof NormalSymptom) {
                    totalWeight += symptom.getWeight();
                }
            }
            double perWeight = 1.0 / totalWeight;
            for (Symptom symptom : symptomList) {
                if (symptom instanceof TypicalSymptom || symptom instanceof NormalSymptom) {
                    symptom.setChance(symptom.getWeight() * perWeight);
                }
            }
        }
        return symptomList;
    }


    public static void main(String[] args) throws TransferException {
        String symptomDesc = symptomNormalization("\"≮早期先天梅毒‡[早产;￥肝脾肿大;￥全身皮损;贫血;血小板减少]|[有的†低出生体重;生后3周左右t+出现临床症状;发育营养差|体重不增|消瘦;反应低下;皮肤皱褶|老人貌;发热;病理性黄疸];￥[皮疹:数目多p+分布广p+不痛不痒p+]ª;￥[斑疹|玫瑰疹：@肢端掌跖部@深红色\\铜红色p+;@口周@@唇周@@肛周@呈放射状糜烂#愈合后形成放射状瘢痕ª]|[扁平湿疣型:@肛周@@外生殖器@稍高出皮面p+界限清楚p+可有糜烂及渗出物p+]|[疱疮：@掌跖部@豌豆大小p+基底暗红色\\基底铜红色p+破溃后糜烂p+]ª;[鼻炎：鼻黏膜≡肥厚\\肿胀;鼻腔≡浆液性\\脓血性≡分泌物&鼻内结痂;严重者†马鞍鼻];￥骨炎|软骨炎|骨髓炎|骨膜炎|肢体剧烈疼痛|假性瘫痪;￥全身淋巴结肿大|肱骨滑车上淋巴结肿大;低蛋白血症|全身水肿|毛发脱落|脉络膜视网膜炎º≯\n" +
                "≮晚期先天梅毒‡￥前额圆凸;￥佩刀胫|胫骨中部前缘骨膜增厚;￥郝秦生齿|上切牙下缘狭窄有半月形凹陷;￥桑葚齿;￥马鞍鼻;￥口腔周围放射状≡皲裂#瘢痕;锁胸骨关节质肥厚;视网膜炎;实质性角膜炎|单侧/双侧角膜深在性浸润|角膜混浊|视力差;神经性耳聋;￥肝脾肿大;￥鼻树胶肿|腭树胶肿;关节积水;骨膜炎;指炎|皮肤黏膜损害;脑膜脑炎|脑积水≯\"\n" );
        System.out.println(chanceSymptom(typeSymptom(toSymptom(TransUtils.splitStrWithBracket(symptomDesc), "症状", "小儿维生素A缺乏病"))));
    }
}
