package main;

import viewer.BoardViewer;
import viewer.UserViewer;

import java.util.Scanner;

//1.번 학생관리 시스템을 mvc 패턴으로 구축하시오.
//2번 사용자 관리시스템을 만드시오
//단, 회원 가입시 동일한 아이디는 만들수없습니다.
//3번. 사원 관리 시스템을 MVC 패턴으로 구축하시오.
public class BoardMain {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        BoardViewer boardViewer = new BoardViewer(scanner);
        UserViewer userViewer = new UserViewer(scanner);

        userViewer.setBoardViewer(boardViewer);
        boardViewer.setUserViewer(userViewer);

        userViewer.showIndex();
    }
}