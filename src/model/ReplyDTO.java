package model;

public class ReplyDTO {
    private int id;
    private int writerId;  //
    private int boardId;   //
    private String content;

    public ReplyDTO() {}

    public ReplyDTO(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getWriterId() {
        return writerId;
    }

    public void setWriterId(int writerId) {
        this.writerId = writerId;
    }

    public int getBoardId() {
        return boardId;
    }

    public void setBoardId(int boardId) {
        this.boardId = boardId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public ReplyDTO(ReplyDTO origin) {
        this.id = origin.id;
        this.writerId = origin.writerId;
        this.boardId = origin.boardId;
        this.content = origin.content;
    }

    public boolean equals(Object o) {
        if (o instanceof ReplyDTO) {
            ReplyDTO r = (ReplyDTO) o;
            return id == r.id;
        }
        return false;
    }

}
