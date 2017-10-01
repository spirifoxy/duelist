package duelist.spirifoxy.com.github.model;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

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

    public Room getLastRoom() {
        return rooms.isEmpty()
                ? null
                : rooms.get(rooms.size()-1);
    }

    public boolean isRoomFilled() {

        return (!rooms.isEmpty() && (getLastRoom().getStatus() == Room.RoomStatus.FILLED));
    }

    private Room findFreeRoom() {

        if (rooms.isEmpty() || //if there is no room with waiting user
                (getLastRoom().getStatus() == Room.RoomStatus.FILLED)) {
            rooms.add(new Room());
        }
        return rooms.get(rooms.size()-1);
    }

}
