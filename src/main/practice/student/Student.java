package practice.student;

/**
 * @author admin
 * @date 2018/1/4
 */
public class Student {
    private String id;
    private String name;
    private double englishScore;
    private double mathScore;
    private double javaScore;

    public Student() {
    }

    public Student(String id, String name, double englishScore, double mathScore, double javaScore) {
        this.id = id;
        this.name = name;
        this.englishScore = englishScore;
        this.mathScore = mathScore;
        this.javaScore = javaScore;
    }

    public Student(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getEnglishScore() {
        return englishScore;
    }

    public void setEnglishScore(double englishScore) {
        this.englishScore = englishScore;
    }

    public double getMathScore() {
        return mathScore;
    }

    public void setMathScore(double mathScore) {
        this.mathScore = mathScore;
    }

    public double getJavaScore() {
        return javaScore;
    }

    public void setJavaScore(double javaScore) {
        this.javaScore = javaScore;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", englishScore=" + englishScore +
                ", mathScore=" + mathScore +
                ", javaScore=" + javaScore +
                '}';
    }
}
