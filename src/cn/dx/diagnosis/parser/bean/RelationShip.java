package cn.dx.diagnosis.parser.bean;

/**
 * 关系
 */
public enum RelationShip {
    AND(0), //与
    OR(1), //或
    NOT(2),//非
    ANDOR(3);//与或同时存在
    private int value = 0;

    RelationShip(int value) {
        this.value = value;
    }

    public static RelationShip valueOf(int value) {
        switch (value) {
            case 0:
                return AND;
            case 1:
                return OR;
            case 2:
                return NOT;
            case 3:
                return ANDOR;
            default:
                return null;
        }
    }

    public int value() {
        return this.value;
    }

}
