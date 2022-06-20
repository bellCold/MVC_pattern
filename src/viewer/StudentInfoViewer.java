package viewer;

import controller.StudentInfoController;
import model.StudentDTO;
import util.ScannerUtil;

import java.util.ArrayList;
import java.util.Scanner;

public class StudentInfoViewer {
    private StudentInfoController studentInfoController;
    private Scanner scanner;
    final int MAX_SCORE = 100;
    final int MIN_SCORE = 0;
    final int SUBJECT_SIZE = 3;

    public StudentInfoViewer() {
        studentInfoController = new StudentInfoController();
        scanner = new Scanner(System.in);
    }

    public void showMenu() {
        while (true) {
            String message = "1.학생정보 입력 2.학생정보 출력 3.종료";
            int userChoice = ScannerUtil.nextInt(scanner, message);
            if (userChoice == 1) {
                writeStudentInfo();
            } else if (userChoice == 2) {
                printStudentInfo();
            } else if (userChoice == 3) {
                System.out.println("종료");
                break;
            }
        }
    }

    private void writeStudentInfo() {
        StudentDTO s = new StudentDTO();
        s.setName(ScannerUtil.nextLine(scanner, "이름 입력"));
        s.setKor(ScannerUtil.nextInt(scanner, "국어 입력", MIN_SCORE, MAX_SCORE));
        s.setEng(ScannerUtil.nextInt(scanner, "영어 입력", MIN_SCORE, MAX_SCORE));
        s.setMath(ScannerUtil.nextInt(scanner, "수학 입력", MIN_SCORE, MAX_SCORE));
        studentInfoController.insert(s);
    }

    private void printStudentInfo() {
        ArrayList<StudentDTO> list = studentInfoController.selectAll();
        if (list.isEmpty()) {
            System.out.println("출력할 학생정보가 없습니다.");
        } else {
            for (StudentDTO s : list) {
                System.out.printf("학생번호: %d 학생이름: %s\n", s.getId(), s.getName());
            }
            String message = "상세보기할 학생 번호나 뒤로 가실려면 0을 입력해주세요.";
            int userChoice = ScannerUtil.nextInt(scanner, message);
            while (userChoice != 0 && studentInfoController.selectOne(userChoice) == null) {
                System.out.println("잘못 입력했습니다.");
                userChoice = ScannerUtil.nextInt(scanner, message);
            }
            if (userChoice != 0) {
                printOne(userChoice);
            }
        }
    }

    private void printOne(int id) {
        StudentDTO studentDTO = studentInfoController.selectOne(id);
        int sum = studentDTO.getKor() + studentDTO.getMath() + studentDTO.getEng();
        double avg = (double) sum / SUBJECT_SIZE;
        System.out.printf("국어: %d점 수학: %d점 영어: %d점\n", studentDTO.getKor(), studentDTO.getMath(), studentDTO.getEng());
        System.out.printf("총점: %d점 평균: %.2f\n", sum, avg);

        int userChoice = ScannerUtil.nextInt(scanner, "1.수정 2.삭제 3.뒤로가기");
        if (userChoice == 1) {
            updateStudentInfo(id);
        } else if (userChoice == 2) {
            deleteStudentInfo(id);
        } else if (userChoice == 3) {
            printStudentInfo();
        }

    }

    private void updateStudentInfo(int id) {
        StudentDTO studentDTO = studentInfoController.selectOne(id);
        studentDTO.setName(ScannerUtil.nextLine(scanner, "수정할 이름"));
        studentDTO.setKor(ScannerUtil.nextInt(scanner, "수정할 국어점수", MIN_SCORE, MAX_SCORE));
        studentDTO.setMath(ScannerUtil.nextInt(scanner, "수정할 수학점수", MIN_SCORE, MAX_SCORE));
        studentDTO.setEng(ScannerUtil.nextInt(scanner, "수정할 영어점수", MIN_SCORE, MAX_SCORE));
        studentInfoController.update(studentDTO);
    }

    private void deleteStudentInfo(int id) {
        String yesNo = ScannerUtil.nextLine(scanner, "정말 삭제하시겠습니까? y/n");
        if (yesNo.equalsIgnoreCase("y")) {
            studentInfoController.delete(id);
        } else {
            printOne(id);
        }

    }
}
