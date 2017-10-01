package duelist.spirifoxy.com.github.model;

import java.util.Objects;

public class User {

    private enum UserStatus {
        UNDEFINED, WAITING, GAMING, WINNER, LOSER
    }

    private Integer id;
    private String username;
    private String password;
    private Integer hp;
    private Integer damage;

    private UserStatus status;

    public User(String username, String password) {
        this(null, username, password, 100, 10);
    }

    public User(Integer id, String username, String password, Integer damage, Integer hp) {
        this.status = UserStatus.UNDEFINED;
        this.id = id;
        this.username = username;
        this.password = password;
        this.damage = damage;
        this.hp = hp;
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

    public String toJSON() {
        return "{" +
                "\"id\": " + getId() + "," +
                "\"username\": " + "\"" + getUsername() + "\"," +
                "\"damage\": " + getDamage() + "," +
                "\"hp\": " + getHp() +
                "}";
    }

    public boolean equals(User user) {
        return Objects.equals(this.username, user.getUsername())
                && Objects.equals(this.hp, user.getHp())
                && Objects.equals(this.damage, user.getDamage());
    }
}
