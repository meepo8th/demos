package designmodel;

/**
 * @author admin
 * @date 2017/12/25
 */
interface AbstractFactory {
    Product build();
}

class ChairFactory implements AbstractFactory {

    @Override
    public Product build() {
        return new ExtendFactory().buildChair();
    }
}

class PcFactory implements AbstractFactory {

    @Override
    public Product build() {
        return new ExtendFactory().buildPC();
    }

}

public class AbstractFactoryTest {
    public static void main(String[] args) {
        System.out.println(new ChairFactory().build().info());
        System.out.println(new PcFactory().build().info());
    }
}