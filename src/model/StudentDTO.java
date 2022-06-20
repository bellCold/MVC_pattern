package model;

public class StudentDTO {
    private int id;
    private String name;
    private int kor;
    private int eng;
    private int math;

    public StudentDTO() {

    }
    public StudentDTO(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getKor() {
        return kor;
    }

    public void setKor(int kor) {
        this.kor = kor;
    }

    public int getEng() {
        return eng;
    }

    public void setEng(int eng) {
        this.eng = eng;
    }

    public int getMath() {
        return math;
    }

    public void setMath(int math) {
        this.math = math;
    }

    public StudentDTO(StudentDTO origin) {
        id = origin.id;
        name = origin.name;
        kor = origin.kor;
        eng = origin.eng;
        math = origin.math;
    }

    public boolean equals(Object o) {
        if (o instanceof StudentDTO) {
            StudentDTO studentDTO = (StudentDTO) o;
            return id == studentDTO.id;
        }
        return false;
    }
}
