package duelist.spirifoxy.com.github.model;

import duelist.spirifoxy.com.github.main.ServerPreferences;
import duelist.spirifoxy.com.github.utils.Utils;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class Room {

    public enum RoomStatus {
        EMPTY, WAITING, FILLED, FIGHTING, USER_UPDATE, FINISHED
    }

    private int id;
    private RoomStatus status;
    private List<User> users;
    private int timeBeforeDuel;
    private Timer timerBeforeDuel;
    private int currentTurn;
    private int lastTurnDamage;

    public Room() {

        id = Utils.getNextRoomNumber();
        currentTurn = ThreadLocalRandom.current().nextInt(0,2);
        status = RoomStatus.EMPTY;
        users = new ArrayList<>();
        timerBeforeDuel = new Timer();
        timeBeforeDuel = ServerPreferences.TIME_BEFORE_DUEL;
        lastTurnDamage = 0;
    }


    public void fill(User user) {
        if (users.size() == 2 ) { //this will never happen, but...
            return;
        }

        user.setRoomId(id);

        User userInRoom = new User(user);
        users.add(userInRoom);

        switch (users.size()) {
            case 1:
                status = RoomStatus.WAITING;
                break;
            case 2:
                status = RoomStatus.FILLED;
                timerBeforeDuel.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        if (timeBeforeDuel > 0) {
                            timeBeforeDuel--;
                        } else {
                            status = RoomStatus.FIGHTING;
                            timerBeforeDuel.cancel();
                            timerBeforeDuel.purge();
                        }
                    }
                }, 1000, 1000);
                break;
            default:
                break;
        }
    }

    public int getId() {
        return id;
    }

    public User getCurrentUser(User currentUser) {
        for (User user: users) {
            if (Objects.equals(user.getUsername(), currentUser.getUsername())) {
                return user;
            }
        }
        return null;
    }

    public User getOpponentUser(User currentUser) {
        for (User user: users) {
            if (!Objects.equals(user.getUsername(), currentUser.getUsername())) {
                return user;
            }
        }
        return null;
    }

    public boolean processAttack(User user) {

        if (status != RoomStatus.FIGHTING) {
            return false;
        }

        User currentUser = users.get(getCurrentTurn());
        if (!user.equals(currentUser)) {
            return false;
        }

        if (status != RoomStatus.USER_UPDATE) {
            status = RoomStatus.USER_UPDATE;
        }

        int damage = ThreadLocalRandom.current().nextInt(currentUser.getDamage()-3, currentUser.getDamage()+3); //to randomize duels
        lastTurnDamage = damage;

        changeTurn();
        User opponentUser = users.get(getCurrentTurn());
        opponentUser.setHp(opponentUser.getHp() - damage);



        if (opponentUser.getHp() <= 0) {
            currentUser.setStatus(User.UserStatus.WINNER);
            opponentUser.setStatus(User.UserStatus.LOSER);
            status = RoomStatus.FINISHED;
        }
        return true;

    }

    private void changeTurn() {
        currentTurn = (getCurrentTurn() + 1) % 2;
    }

    public int getUserNumber(User user) {
        return users.indexOf(user);
    }

    public RoomStatus getStatus() {
        return status;
    }

    public void setStatus(RoomStatus status) {
        this.status = status;
    }

    public int getTimeToStart() {
        return timeBeforeDuel;
    }

    public int getCurrentTurn() {
        return currentTurn;
    }

    public boolean isNowUsersTurn(User user) {
        return getUserNumber(getCurrentUser(user)) == getCurrentTurn();
    }

    public User getWinner() {
        for (User user:users) {
            if (user.getStatus() == User.UserStatus.WINNER) {
                return user;
            }
        }
        return null;
    }

    public User getLoser() {
        for (User user:users) {
            if (user.getStatus() == User.UserStatus.LOSER) {
                return user;
            }
        }
        return null;
    }

    public int getLastTurnDamage() {
        return lastTurnDamage;
    }
}
