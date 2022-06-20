package controller;

import model.UserDTO;

import java.util.ArrayList;

public class UserController {

    private ArrayList<UserDTO> list;
    private int nextId;

    public UserController() {
        list = new ArrayList<>();
        nextId = 1;
    }

    //회원 등록
    public void insert(UserDTO u) {
        u.setId(nextId++);
        list.add(u);
    }

    //아이디 중복체크
    public boolean validateUsername(String username) {
        for (UserDTO u : list) {
            if (u.getUsername().equalsIgnoreCase(username)) {
                return true;
            }
        }
        return false;
    }


    //일치회원 체크
    public UserDTO auth(String username, String password) {
        for (UserDTO u : list) {
            if (u.getUsername().equalsIgnoreCase(username) && u.getPassword().equals(password)) {
                return new UserDTO(u);
            }
        }
        return null;
    }

    //회원탈퇴
    public void delete(int id) {
        list.remove(new UserDTO(id));
    }

    //회원수정
    public void update(UserDTO u) {
        int index = list.indexOf(u);
        list.set(index, u);
    }

    public UserDTO selectOne(int id) {
        UserDTO u = new UserDTO(id);
        if (list.contains(u)) {
            return new UserDTO(list.get(list.indexOf(u)));
        }

        return null;
    }



































    /* 종찬*/
/*    private ArrayList<UserDTO> list;
    private int nextId;

    public UserController() {
        list = new ArrayList<>();
        nextId = 1;
    }

    public void insert(UserDTO userDTO) {
        userDTO.setId(nextId++);
        list.add(userDTO);
    }

    public void delete(int id) {
        list.remove(new UserDTO(id));
    }

    public void update(UserDTO userDTO) {
        int index = list.indexOf(userDTO);
        list.set(index, userDTO);
    }

    public ArrayList<UserDTO> selectAll() {
        ArrayList<UserDTO> temp = new ArrayList<>();
        for (UserDTO userDTO : list) {
                temp.add(new UserDTO(userDTO));
        }
        return temp;
    }

    public UserDTO selectOne(int id) {
        for (UserDTO userDTO : list) {
            if (userDTO.getId() == id) {
                return new UserDTO(userDTO);
            }
        }
        return null;
    }
    public UserDTO selectId(String userId){
        for (UserDTO userDTO : list) {
            if (userDTO.getUserId() == userId) {
                return new UserDTO(userDTO);
            }
        }
        return null;
    }*/
}
