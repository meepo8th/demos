public class Singleton {
    private static class SigletonHolder {
        private final static Singleton singleton = new Singleton();
    }

    private Singleton() {

    }

    public static Singleton getInstance() {
        return SigletonHolder.singleton;
    }
}

/**
 * 点类
 */
class Point {
    Double x;
    Double y;

    public Double getX() {
        return x;
    }

    public void setX(Double x) {
        this.x = x;
    }

    public Double getY() {
        return y;
    }

    public void setY(Double y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "Point{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}

/**
 * 圆类
 */
class Circle {
    Point center;
    Double r;

    public Point getCenter() {
        return center;
    }

    public void setCenter(Point center) {
        this.center = center;
    }

    public Double getR() {
        return r;
    }

    public void setR(Double r) {
        this.r = r;
    }

    /**
     * 获得周长
     *
     * @return
     */
    public double getPerimeter() {
        return Math.PI * 2 * r;
    }

    /**
     * 获得面积
     *
     * @return
     */
    public double getArea() {
        return Math.PI * Math.pow(r, 2);
    }

    @Override
    public String toString() {
        return "Circle{" +
                "center=" + center +
                ", r=" + r +
                '}';
    }
}