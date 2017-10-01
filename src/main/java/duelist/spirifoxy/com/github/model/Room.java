package duelist.spirifoxy.com.github.model;

import duelist.spirifoxy.com.github.main.ServerPreferences;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
public class Room {

    public enum RoomStatus {
        EMPTY, WAITING, FILLED
    }

    private RoomStatus status;
    private List<User> users;


    public Room() {
        status = RoomStatus.EMPTY;
        users = new ArrayList<>();
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
                break;
            default:
                break;
        }
    }

    public RoomStatus getStatus() {
        return status;
    }
}
