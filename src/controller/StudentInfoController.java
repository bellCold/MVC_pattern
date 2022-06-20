package controller;

import model.StudentDTO;

import java.util.ArrayList;

public class StudentInfoController {
    private ArrayList<StudentDTO> list;
    private int nextId;

    public StudentInfoController() {
        list = new ArrayList<>();
        nextId = 1;
    }

    public void insert(StudentDTO s) {
        s.setId(nextId++);
        list.add(s);
    }

    public ArrayList<StudentDTO> selectAll() {
        ArrayList<StudentDTO> temp = new ArrayList<>();
        for (StudentDTO s : list) {
            temp.add(new StudentDTO(s));
        }
        return temp;
    }

    public StudentDTO selectOne(int id) {
        for (StudentDTO s : list) {
            if (s.getId() == id) {
                return new StudentDTO(s);
            }
        }
        return null;
    }

    public void update(StudentDTO studentDTO) {
        int index = list.indexOf(studentDTO);
        list.set(index, studentDTO);
    }
    public void delete(int id) {
        list.remove(new StudentDTO(id));
    }
}

