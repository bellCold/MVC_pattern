package controller;

import model.ReplyDTO;

import java.util.ArrayList;

public class ReplyController {
    private ArrayList<ReplyDTO> list;
    private int nextId;

    public ReplyController() {
        list = new ArrayList<>();
        nextId = 1;
    }

    public void insert(ReplyDTO r) {
        r.setId(nextId++);
        list.add(r);
    }

    public ArrayList<ReplyDTO> selectAll(int id) {
        ArrayList<ReplyDTO> temp = new ArrayList<>();
        for (ReplyDTO r : list) {
            temp.add(new ReplyDTO(r));
        }
        return temp;

    }

    public ReplyDTO selectOne(int id) {
        for (ReplyDTO r : list) {
            if (r.getId() == id) {
                return new ReplyDTO(id);
            }
        }
        return null;
    }

    public void update(ReplyDTO r) {
        int index = list.indexOf(r);
        list.set(index, r);
    }

    public void delete(int id) {
        list.remove(new ReplyDTO(id));
    }

}
