package demo;

import java.io.*;
import java.util.ArrayList;

/**
 * @author Administrator
 */
public class Student implements Serializable {
    private String stuID;
    private String name;
    private Double score;

    public Student() {
    }

    public Student(String stuID, String name, Double score) {
        this.stuID = stuID;
        this.name = name;
        this.score = score;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{\"Student\":{");
        sb.append("\"stuID\":\"")
                .append(stuID).append('\"');
        sb.append(",\"name\":\"")
                .append(name).append('\"');
        sb.append(",\"score\":")
                .append(score);
        sb.append("}}");
        return sb.toString();
    }

    public void show() {
        System.out.println(toString());
    }

    public static void saveStudents(ArrayList<Student> students, String fileName) throws IOException {
        File saveFile = new File(fileName);
        if (!saveFile.exists()) {
            if (!saveFile.getParentFile().exists()) {
                saveFile.getParentFile().mkdirs();
            }
            saveFile.createNewFile();
        }
        try (ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(saveFile))) {
            os.writeObject(students);
            os.flush();
        }
    }

    public static ArrayList<Student> loadStudents(String fileName) throws IOException, ClassNotFoundException {
        ArrayList<Student> students = new ArrayList<>();
        File saveFile = new File(fileName);
        if (saveFile.exists()) {
            try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(saveFile))) {
                students = (ArrayList<Student>) in.readObject();
            }
        }
        return students;

    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        System.out.println(new Double(5.5).intValue());
        System.out.println(Math.floor(5.5));
        ArrayList<Student> list = new ArrayList<>();
        String dataFile = "f:/123/456/test.dat";
        list.add(new Student("1", "zhang", 35D));
        list.add(new Student("2", "wang", 40D));
        list.add(new Student("3", "liu", 50D));
        saveStudents(list, dataFile);
        ArrayList<Student> students = loadStudents(dataFile);
        for (Student student : students) {
            student.show();
        }
    }
}
