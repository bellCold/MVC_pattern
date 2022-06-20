package viewer;

import controller.UserController;
import model.UserDTO;
import util.ScannerUtil;

import java.util.Scanner;

public class UserViewer {
    private UserController userController;
    private Scanner scanner;
    private UserDTO lonIn;
    private BoardViewer boardViewer;

    public UserViewer(Scanner scanner) {
        userController = new UserController();
        this.scanner = scanner;
    }

    public void setBoardViewer(BoardViewer boardViewer) {
        this.boardViewer = boardViewer;
    }

    public void showIndex() {
        while (true) {
            int userChoice = ScannerUtil.nextInt(scanner, "1.로그인 2.회원가입 3.종료");

            if (userChoice == 1) {
                logIn();
                if (lonIn != null) {
                    boardViewer.setLogIn(lonIn);
                    showMenu();
                }
            } else if (userChoice == 2) {
                register();
            } else if (userChoice == 3) {
                System.out.println("종료");
                break;
            }
        }
    }

    private void register() {
        String username = ScannerUtil.nextLine(scanner, "사용하실 아이디를 입력해주시거나 뒤로 가실려면 X를 입력해주세요.");
        while (!username.equalsIgnoreCase("x") && userController.validateUsername(username)) {
            System.out.println("잘못입력하셨습니다.");
            username = ScannerUtil.nextLine(scanner, "사용하실 아이디를 입력해주시거나 뒤로 가실려면 X를 입력해주세요.");
        }
        if (!username.equalsIgnoreCase("x")) {
            String password = ScannerUtil.nextLine(scanner, "사용하실 비밀번호를 입력해주세요.");
            String nickname = ScannerUtil.nextLine(scanner, "사용하실 닉네임을 입력해주세요.");
            UserDTO u = new UserDTO();
            u.setUsername(username);
            u.setPassword(password);
            u.setNickname(nickname);
            userController.insert(u);
        }
    }


    private void logIn() {
        String username = ScannerUtil.nextLine(scanner, "아이디를 입력해주세요.");
        String password = ScannerUtil.nextLine(scanner, "비밀번호를 입력해주세요.");

        while (userController.auth(username, password) == null) {
            System.out.println("잘못 입력하셨습니다.");
            String yesNo = ScannerUtil.nextLine(scanner, "로그인을 그만하시겠습니까? y/n");
            if (yesNo.equalsIgnoreCase("Y")) {
                password = null;
                break;
            }
            username = ScannerUtil.nextLine(scanner, "아이디를 입력해주세요.");
            password = ScannerUtil.nextLine(scanner, "비밀번호를 입력해주세요.");
        }

        lonIn = userController.auth(username, password);

    }

    private void showMenu() {
        while (lonIn != null) {
            int userChoice = ScannerUtil.nextInt(scanner, "1.게시판 2.회원 정보 상세보기 2.로그아웃");
            if (userChoice == 1) {
                boardViewer.showMenu();
            } else if (userChoice == 2) {
                printOne();
            } else if (userChoice == 3) {
                System.out.println("로그아웃 되었습니다.");
                lonIn = null;
            }
        }
    }

    private void printOne() {
        System.out.println("회원 username: " + lonIn.getUsername());
        System.out.println("회원 nickname: " + lonIn.getNickname());

        int userChoice = ScannerUtil.nextInt(scanner, "1.정보 수정 2.회원 탈퇴 3. 뒤로가기");
        if (userChoice == 1) {
            update();
        } else if (userChoice == 2) {
            delete();
        }
    }

    private void update() {
        String oldPassword = ScannerUtil.nextLine(scanner, "기존 비밀번호를 입력");
        while (!lonIn.getPassword().equals(oldPassword)) {
            oldPassword = ScannerUtil.nextLine(scanner, "잘못입력했습니다 다시 입력해주세요.");
        }
        if (lonIn.getPassword().equals(oldPassword)) {
            String password = ScannerUtil.nextLine(scanner, "변경하실 비밀번호를 입력해주세요.");
            String nickname = ScannerUtil.nextLine(scanner, "변경하실 닉네임을 입력해주세요.");
            lonIn.setPassword(password);
            lonIn.setNickname(nickname);
            userController.update(lonIn);
        }

        printOne();
    }

    private void delete() {
        String yesNo = ScannerUtil.nextLine(scanner, "정말로 탈퇴하시겠습니까? y/n");
        if (yesNo.equalsIgnoreCase("y")) {
            String oldPassword = ScannerUtil.nextLine(scanner, "비밀번호를 입력해주세요.");
            if (oldPassword.equals(lonIn.getPassword())) {
                //해당아이디 객체 삭제 동시에 해당객체 글도 삭제
                userController.delete(lonIn.getId());
                boardViewer.deleteByWriterId(lonIn.getId());
                lonIn = null;
            }
        }
        if (lonIn != null) {
            printOne();
        }
    }

    public void printNickname(int id) {
        UserDTO u = userController.selectOne(id);
        System.out.println(u.getNickname());
    }















    /* 종찬 */
   /* private UserController userController;
    private Scanner scanner;

    public UserViewer() {
        userController = new UserController();
        scanner = new Scanner(System.in);
    }

    public void showMenu() {
        while (true) {
            int userChoice = ScannerUtil.nextInt(scanner, "1.사용자 등록 2.사용자 보기 3. 종료");
            if (userChoice == 1) {
                registerUser();
            } else if (userChoice == 2) {
                showUserInfo();
            } else if (userChoice == 3) {
                System.out.println("시스템 종료");
                break;
            }
        }
    }

    private void registerUser() {
        UserDTO userDTO = new UserDTO();
        ArrayList<UserDTO> list = new ArrayList<>();
        String userChoiceId = ScannerUtil.nextLine(scanner, "아이디를 입력해주세요.");
        for (UserDTO userDTO1 : list) {
            while (userDTO1.getUserId().equals(userChoiceId)) {
                System.out.println("다시 입력");
            }
        }
        userDTO.setUserId(userChoiceId);
        userDTO.setUserPassword(ScannerUtil.nextLine(scanner, "패스워드 입력"));
        userController.insert(userDTO);
    }

    private void showUserInfo() {
        ArrayList<UserDTO> list = userController.selectAll();
        if (list.isEmpty()) {
            System.out.println("등록된 사용자가 없습니다.");
        } else {
            for (UserDTO userDTO : list) {
                System.out.printf("사용자 번호: %d 아이디: %s \n", userDTO.getId(), userDTO.getUserId());
            }
            int userChoice = ScannerUtil.nextInt(scanner, "상세보기 사용자번호 뒤로가기0");
            while (userChoice != 0 && userController.selectOne(userChoice) == null) {
                userChoice = ScannerUtil.nextInt(scanner, "다시 입력바랍니다");
            }
            if (userChoice != 0) {
                printOne(userChoice);
            }
        }
    }

    private void printOne(int id) {
        UserDTO userDTO = userController.selectOne(id);
        System.out.printf("userId: %s userPassword: %s\n", userDTO.getUserId(), userDTO.getUserPassword());

        int userChoice = ScannerUtil.nextInt(scanner, "1.수정 2.삭제 /0뒤로가기");
        if (userChoice == 1) {
            updateUserInfo(id);
        } else if (userChoice == 2) {
            deleteUserInfo(id);
        } else if ( userChoice == 3) {
            showUserInfo();
        }

    }

    private void updateUserInfo(int id) {
        UserDTO userDTO = userController.selectOne(id);
        userDTO.setUserId(ScannerUtil.nextLine(scanner,"새 아이디 입력바람"));
        userDTO.setUserPassword(ScannerUtil.nextLine(scanner, "새 비밀번호 입력바람"));
        userController.update(userDTO);
    }

    private void deleteUserInfo(int id) {
        String yesNo = ScannerUtil.nextLine(scanner, "삭제하시겠습니까 y/n");
        if (yesNo.equalsIgnoreCase("y")) {
            userController.delete(id);
            System.out.println("삭제되었습니다.");
        } else {
            printOne(id);
        }
    }
*/
}
