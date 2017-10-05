package duelist.spirifoxy.com.github.model;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class Server {

    private List<Room> rooms;

    private Server() {
        rooms = new ArrayList<>();

    }

    private static class ServerHelper {

        private static final Server instance = new Server();
    }

    public static Server getInstance() {

        return ServerHelper.instance;
    }

    public void connectUser(User user) {

        Room room = findFreeRoom();
        room.fill(user);
    }

    public int getTimeBeforeDuel(int id) {
        return findRoomById(id).getTimeToStart();
    }

    public boolean processAttack(User user) {
        return findRoomById(user.getRoomId()).processAttack(user);
    }

    /*public User getCurrentUser(User user) {
        return getLastRoom().getCurrentUser(user);
    }

    public User getOpponentUser(User user) {
        return getLastRoom().getOpponentUser(user);
    }*/

    public Room.RoomStatus getRoomStatus(int id) {
        return findRoomById(id).getStatus();
    }

    public void setRoomStatus(int id, Room.RoomStatus status) {
        findRoomById(id).setStatus(status);
    }

    public boolean isRoomFilled(int id) {

        return (!rooms.isEmpty() && (findRoomById(id).getStatus() == Room.RoomStatus.FILLED));
    }

    public boolean isNowUsersTurn(User user) {
        return findRoomById(user.getRoomId()).isNowUsersTurn(user);
    }

    private Room findFreeRoom() {

        if (rooms.isEmpty() || //if there is no room with waiting user
                (getNewestRoom().getStatus() != Room.RoomStatus.WAITING)) {
            rooms.add(new Room());
        }
        return getNewestRoom();
    }

    public Room getNewestRoom() {
        return rooms.isEmpty()
                ? null
                : rooms.get(rooms.size()-1);
    }

    public Room findRoomById(int id) {
        for (Room room: rooms) {
            if (room.getId() == id)
                return room;
        }
        return null;
    }

    public User getWinner(int roomId) {
        Room room = findRoomById(roomId);
        if (room.getStatus() != Room.RoomStatus.FINISHED) {
            return null;
        }
        return room.getWinner();
    }

    public User getLoser(int roomId) {
        Room room = findRoomById(roomId);
        if (room.getStatus() != Room.RoomStatus.FINISHED) {
            return null;
        }
        return room.getLoser();
    }

    public int getLastTurnDamage(int roomId) {
        return findRoomById(roomId).getLastTurnDamage();
    }

}
