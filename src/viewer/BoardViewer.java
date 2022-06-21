package viewer;

import controller.BoardController;
import model.BoardDTO;
import model.ReplyDTO;
import model.UserDTO;
import util.ScannerUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/*
뷰어
뷰어는 사용자가 직접적으로 보게되는 화면단, 즉 프론트엔드가 된다
단, 지금 현재 우리는 console에서 프로그래밍을 하므로
뷰어가 java 클래스가 된다.

또한, 원칙적으로는 데이터베이스가 존재하기 때문에
우리가 필요하면 그 즉시 해당 메소드에서 controller 객체를 생성해서
해당 객체의 필요한 메소드를 형성시키는게 맞지만 없다
그래서 어쩔 수 없디 ,BoardController 필드를 하나 여기에 만들어주게된다.
 */
public class BoardViewer {
    private BoardController controller;
    private Scanner scanner;
    private UserViewer userViewer;
    private ReplyViewer replyViewer;
    private UserDTO logIn;

    public BoardViewer(Scanner scanner) {
        controller = new BoardController();
        this.scanner = scanner;
    }

    public void setReplyViewer(ReplyViewer r) {
        this.replyViewer = r;
    }

    public void setLogIn(UserDTO logIn) {
        this.logIn = logIn;
    }

    public void setUserViewer(UserViewer userViewer) {
        this.userViewer = userViewer;
    }

    //게시글 관련 메뉴 메소드
    public void showMenu() {
        while (true) {
            String message = "1.새 글 작성 2. 글 목록 보기 3. 뒤로가기";
            int userChoice = ScannerUtil.nextInt(scanner, message);
            if (userChoice == 1) {
                //새 글 작성 메소드
                writeBoard();
            } else if (userChoice == 2) {
                //글 목록 출력 메소드
                printList();
            } else if (userChoice == 3) {
                System.out.println("뒤로가기");
                break;
            }
        }
    }

    private void writeBoard() {
        BoardDTO b = new BoardDTO();
        String message = "글의 제목을 입력해주세요.";
        b.setTitle(ScannerUtil.nextLine(scanner, message));
        b.setWriterId(logIn.getId());
        message = "글의 내용을 입력해주세요.";
        b.setContent(ScannerUtil.nextLine(scanner, message));
        controller.insert(b);
    }

    private void printList() {
        ArrayList<BoardDTO> list = controller.selectAll();
        if (list.isEmpty()) {
            System.out.println("아직 등록된 글이 존재하지 않습니다.");
        } else {
            Collections.reverse(list);
            for (BoardDTO b : list) {
                System.out.printf("%d. %s\n", b.getId(), b.getTitle());
            }


            String message = "상세보기할 글의 번호나 뒤로 가실려면 0을 입력해주세요.";
            int userChoice = ScannerUtil.nextInt(scanner, message);
            while (userChoice != 0 && controller.selectOne(userChoice) == null) {
                System.out.println("잘못 입력했습니다.");
                userChoice = ScannerUtil.nextInt(scanner, message);
            }
            if (userChoice != 0) {
                printOne(userChoice);
            }
        }
    }

    private void printOne(int id) {
        BoardDTO b = controller.selectOne(id);
        System.out.println("제목: " + b.getTitle());
        System.out.println("글번호" + id);
        System.out.print("작성자: ");
        userViewer.printNickname(b.getWriterId());
        System.out.println();
        System.out.println("글 내용: " + b.getContent());

        /*댓글 보여주기 replyViewer*/
        replyViewer.showReply(id);
        String message;
        if (b.getWriterId() == logIn.getId()) {
            message = "1.수정 2.삭제 3.댓글등록 4.댓글수정 5.댓글삭제 6.뒤로가가기";
            int userChoice = ScannerUtil.nextInt(scanner, message);
            if (userChoice == 1) {
                updateBoard(id);
            } else if (userChoice == 2) {
                deleteBoard(id);
            } else if (userChoice == 3) {
                replyViewer.writeReply(id);
                printOne(id);
            } else if (userChoice == 4) {
                replyViewer.updateReply();
            } else if (userChoice == 5) {
                replyViewer.deleteReply();
            } else {
                printList();
            }
        } else {
            int userChoice = ScannerUtil.nextInt(scanner, "1.뒤로가기");

            printList();
        }

    }

    private void updateBoard(int id) {
        BoardDTO b = controller.selectOne(id);
        b.setTitle(ScannerUtil.nextLine(scanner, "새로운 제목을 입력해주세요."));
        b.setContent(ScannerUtil.nextLine(scanner, "새로운 내용을 입력해주세요."));
        controller.update(b);
        printOne(id);
    }

    private void deleteBoard(int id) {
        String yesNo = ScannerUtil.nextLine(scanner, "정말로? Y/N");
        if (yesNo.equalsIgnoreCase("y")) {
            controller.delete(id);
            printList();
        } else {
            printOne(id);
        }
    }

    public void deleteByWriterId(int writerId) {
        controller.deleteByWriterId(writerId);
    }

    /* reply viewer */

}
