package duelist.spirifoxy.com.github.model;

public class User {

    private Integer id;
    private String username;
    private String password;
    private Integer hp;
    private Integer damage;

    public User(String username, String password) {
        this(null, username, password, 100, 10);
    }

    public User(Integer id, String username, String password, Integer hp, Integer damage) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.hp = hp;
        this.damage = damage;
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

}
