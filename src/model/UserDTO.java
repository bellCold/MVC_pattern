package model;

public class UserDTO {
    /*bit*/
    private int id;
    private String username;
    private String password;
    private String nickname;

    public UserDTO() {}
    public UserDTO(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public boolean equals(Object o) {
        if (o instanceof UserDTO) {
            UserDTO u = (UserDTO) o;
            return id == u.id;
        }
        return false;
    }

    public UserDTO(UserDTO u) {
        this.id = u.id;
        this.username = u.username;
        this.password = u .password;
        this.nickname = u.nickname;
    }

    /* kim */
    /*private int id;
    private String userId;
    private String userPassword;

    public UserDTO(){}

    public UserDTO(int id) {
        this.id = id;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public UserDTO(UserDTO origin) {
        id = origin.id;
        userId = origin.userId;
        userPassword = origin.userPassword;
    }

    public boolean equals(Object o) {
        if (o instanceof UserDTO) {
            UserDTO userDTO = (UserDTO) o;
            return id == userDTO.id;
        }
        return false;
    }*/
}
