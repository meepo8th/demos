package demo;

import java.util.ArrayList;
import java.util.List;

public class GcDemo {

    public static void main(String[] args) {
        if (true) {
            while (true) {
                List<String> stringList = new ArrayList<>(10000);
            }
        }
    }

}