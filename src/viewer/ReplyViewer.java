package viewer;

import controller.ReplyController;
import model.ReplyDTO;
import model.UserDTO;
import util.ScannerUtil;

import java.util.ArrayList;
import java.util.Scanner;

public class ReplyViewer {
    private ReplyController replyController;
    private Scanner scanner;
    private UserDTO login;

    public ReplyViewer(Scanner scanner) {
        this.scanner = scanner;
        replyController = new ReplyController();
    }

    public void setLogIn(UserDTO logIn) {
        this.login = logIn;
    }

    public void showReply(int id) {
        ArrayList<ReplyDTO> list = replyController.selectAll(id);
        if (list.isEmpty()) {
            System.out.println("댓글이 없습니다.");
        } else {
            for (ReplyDTO r : list) {
                System.out.printf("댓글번호:%d 댓글: %s \n", r.getId(), r.getContent());

            }
        }
    }

    public void writeReply(int id) {
        ReplyDTO r = new ReplyDTO(id);
        r.setContent(ScannerUtil.nextLine(scanner, "댓글을 입력해주세요."));
        r.setBoardId(id);
        r.setWriterId(login.getId());
        replyController.insert(r);
    }

    public void updateReply() {
        int updateReplyNum = ScannerUtil.nextInt(scanner, "수정할 댓글번호");
        ReplyDTO r = replyController.selectOne(updateReplyNum);
        if (replyController.selectOne(updateReplyNum) == null) {
            System.out.println("없는 댓글번호입니다.");
        } else {
            String newContent = ScannerUtil.nextLine(scanner, "수정할 댓글입력");
            r.setContent(newContent);
            replyController.update(r);
        }
    }

    public void deleteReply() {
        int deleteReplyNum = ScannerUtil.nextInt(scanner, "삭제할 댓글번호");if (replyController.selectOne(deleteReplyNum) == null) {
            System.out.println("없는 댓글번호입니다.");
        } else {
            String yesNo = ScannerUtil.nextLine(scanner, "정말 삭제하시겠습니까? y/n");
            if (yesNo.equalsIgnoreCase("y")) {
                replyController.delete(deleteReplyNum);
                System.out.println("댓글이 삭제되었습니다.");
            }
        }
    }

}