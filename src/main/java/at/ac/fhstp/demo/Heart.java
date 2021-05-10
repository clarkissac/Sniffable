package at.ac.fhstp.demo;

public class Heart {
    int age;
    String sex;
    String cp;
    String trtbps;
    String chol;
    
    public Heart(int age, String sex, String cp, String trtbps, String chol) {
        this.age = age;
        this.sex = sex;
        this.cp = cp;
        this.trtbps = trtbps;
        this.chol = chol;
    }

    public int getAge() {
        return age;
    }

    public static Heart fromCSVLine(String line) {
        String[] p= line.split(",");
        return new Heart(
                Integer.parseInt(p[0]), p[1], p[2], p[3], p[4]
        );
    }

    @Override
    public String toString() {
        return "Age: " + age + " Sex: " + sex + " CP: " + cp + " TRTBPS: " + trtbps + " chol: " + chol+ "\n";
    }

}
