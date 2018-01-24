package practice.student;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author admin
 * @date 2018/1/4
 */
public class StudentManager {
    Student[] students;
    int maxSize = 40;
    int size;


    private void init() {
        students = new Student[maxSize];
    }

    public StudentManager() {
        init();
    }

    public StudentManager(int maxSize) {
        this.maxSize = maxSize;
        init();
    }

    public void add(Student student) {
        if (size >= maxSize) {
            System.out.println("当前已达到最大学生数，无法添加");
        } else {
            students[size++] = student;
        }
    }

    public void remove(String id) {
        for (int i = 0; i < size; i++) {
            if (id.equals(students[i].getId())) {
                System.arraycopy(students, i + 1, students, i, size - i - 1);
                size--;
                break;
            }
        }
    }

    public void show(String id) {
        System.out.println("start show");
        for (int i = 0; i < size; i++) {
            if (id.equals(students[i].getId())) {
                System.out.println(students[i]);
                break;
            }
        }
        System.out.println("end show");
    }

    public void show() {
        System.out.println("start show");
        for (int i = 0; i < size; i++) {
            System.out.println(students[i]);
        }
        System.out.println("end show");
    }

    public void sort() {
        Arrays.sort(students, 0, size, new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                double sub = ((o1.getEnglishScore() + o1.getJavaScore() + o1.getMathScore())
                        - (o2.getEnglishScore() + o2.getJavaScore() + o2.getMathScore()));
                return sub >= 0 ? (sub == 0 ? 0 : -1) : 1;
            }
        });
    }
}
