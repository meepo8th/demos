package designmodel;

/**
 * Created by admin on 2017/12/25.
 *
 * @author admin
 */
interface Product {
    default String info() {
        return this.getClass().getSimpleName();
    }
}

class Computer implements Product {

}

class Chair implements Product {

}

class ExtendFactory {
    public Computer buildPC() {
        return new Computer();
    }

    public Chair buildChair() {
        return new Chair();
    }
}

/**
 * @author admin
 */
public class Factory {
    public static void main(String[] args) {
        System.out.println(new Factory().build("PC").info());
        System.out.println(new Factory().build("CHAIR").info());
        System.out.println(new Factory().build("OTHER").info());
    }

    public Product build(String type) {
        switch (type) {
            case "PC":
                return new Computer();
            case "CHAIR":
                return new Chair();
            default:
                return null;
        }
    }
}


