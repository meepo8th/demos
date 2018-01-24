package game.jdqs.weapon;

/**
 * 武器
 *
 * @author admin
 * @date 2017/12/26
 */
public abstract class Weapon {
    /**
     * 耐久度
     */
    int durability;

    /**
     * 攻击
     */
    abstract void attack();
}
