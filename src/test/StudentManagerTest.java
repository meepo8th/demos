package practice.student;

import org.junit.Test;

public class StudentManagerTest {
    @Test
    public void studentManagerTest() {
        StudentManager manager = new StudentManager(80);
        manager.show();
        manager.add(new Student("2", "zhang2", 49, 60, 70));
        manager.add(new Student("1", "zhang1", 50, 60, 70));
        manager.add(new Student("3", "zhang3", 52, 60, 70));
        manager.add(new Student("4", "zhang4", 53, 60, 90));
        manager.show();
        manager.sort();
        manager.show();
        manager.show("1");
        manager.remove("1");
        manager.show();
        manager.remove("1");
        manager.show();
        manager.add(new Student("1", "zhang1", 50, 60, 70));
        manager.show();

    }
}
