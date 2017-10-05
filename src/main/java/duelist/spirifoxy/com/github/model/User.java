package duelist.spirifoxy.com.github.model;

import java.util.Objects;

public class User {

    public enum UserStatus {
        UNDEFINED, WAITING, GAMING, WINNER, LOSER
    }

    private Integer id;
    private String username;
    private String password;
    private Integer hp;
    private Integer damage;
    private Integer rating;

    private UserStatus status;
    private Integer roomId;

    public User(String username, String password) {
        this(null, username, password, 10, 100, 0, UserStatus.UNDEFINED,null);
    }

    public User(User user) {
        this(user.getId(), user.getUsername(), user.getPassword(), user.getDamage(), user.getHp(), user.getRating(), user.getStatus(), user.getRoomId());
    }

    public User(Integer id, String username, String password, Integer damage, Integer hp, Integer rating) {
        this(null, username, password, damage, hp, rating, UserStatus.UNDEFINED, null);
    }

    public User(Integer id, String username, String password, Integer damage, Integer hp, Integer rating, UserStatus status, Integer roomId) {

        this.status = status;
        this.roomId = roomId;

        this.id = id;
        this.username = username;
        this.password = password;
        this.damage = damage;
        this.hp = hp;
        this.rating = rating;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public Integer getDamage() {
        return damage;
    }

    public void setDamage(Integer damage) {
        this.damage = damage;
    }

    public Integer getHp() {
        return hp;
    }

    public void setHp(Integer hp) {
        this.hp = hp;
    }

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }

    public Integer getRoomId() {
        return roomId;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String toJSON() {
        return "{" +
                "\"id\": " + getId() + "," +
                "\"username\": " + "\"" + getUsername() + "\"," +
                "\"damage\": " + getDamage() + "," +
                "\"hp\": " + getHp() +
                "}";
    }

    public boolean equals(User user) {
        return Objects.equals(this.username, user.getUsername());
    }

    public void update(boolean isUserWinner) {
        hp += 1;
        damage += 1;
        rating += isUserWinner ? 1 : -1;
    }
}
