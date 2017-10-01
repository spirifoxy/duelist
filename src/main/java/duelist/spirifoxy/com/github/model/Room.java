package duelist.spirifoxy.com.github.model;

import duelist.spirifoxy.com.github.main.ServerPreferences;

import java.util.*;

public class Room {

    public enum RoomStatus {
        EMPTY, WAITING, FILLED, FAITING
    }

    private RoomStatus status;
    private List<User> users;
    private int timeBeforeDuel;
    private Timer timerBeforeDuel;


    public Room() {
        status = RoomStatus.EMPTY;
        users = new ArrayList<>();
        timerBeforeDuel = new Timer();
        timeBeforeDuel = ServerPreferences.TIME_BEFORE_DUEL;
    }


    public void fill(User user) {
        if (users.size() == 2 ) { //this will never happen, but...
            return;
        }

        users.add(user);

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
                            status = RoomStatus.FAITING;
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

    public User getOpponentUser(User currentUser) {
        for (User user: users) {
            if (!Objects.equals(user.getUsername(), currentUser.getUsername())) {
                return user;
            }
        }
        return null;
    }

    public RoomStatus getStatus() {
        return status;
    }

    public int getTimeToStart() {
        return timeBeforeDuel;
    }
}
