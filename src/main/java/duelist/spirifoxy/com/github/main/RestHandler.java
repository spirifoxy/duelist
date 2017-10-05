package duelist.spirifoxy.com.github.main;

import duelist.spirifoxy.com.github.db.UserDaoMysql;
import duelist.spirifoxy.com.github.model.Room;
import duelist.spirifoxy.com.github.model.Server;
import duelist.spirifoxy.com.github.model.User;
import duelist.spirifoxy.com.github.utils.Utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import java.util.Objects;

@Path("/")
public class RestHandler {
    private Server server = Server.getInstance();

    @POST
    @Path("/onConnect/")
    public Response onConnect(@Context HttpServletRequest request) {

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        server.connectUser(user);

        return Response.status(Response.Status.OK).entity(user.getUsername()).build();
    }

    @GET
    @Path("/isRoomFilled")
    public Response isRoomFilled(@Context HttpServletRequest request) {

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        boolean isRoomFilled = server.isRoomFilled(user.getRoomId());
        if (isRoomFilled) {
            Room room = server.getNewestRoom();
            User opponent = new User(room.getOpponentUser(user));
            session.setAttribute("room", room);
            session.setAttribute("opponent", opponent);
        }

        return Response.status(Response.Status.OK).entity(Boolean.toString(isRoomFilled)).build();
    }

    @GET
    @Path("/timeBeforeDuel")
    public Response getTimeBeforeDuel(@Context HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        int time = server.getTimeBeforeDuel(user.getRoomId());

        return Response.status(Response.Status.OK).entity(Integer.toString(time)).build();
    }

    @POST
    @Path("/attack")
    public Response onAttack(@Context HttpServletRequest request) {

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        boolean isAttackProcessed = server.processAttack(user);

        return Response.status(Response.Status.OK).entity(Boolean.toString(isAttackProcessed)).build();
    }

    @GET
    @Path("/updateUserInfo")
    public Response updateUserInfo(@Context HttpServletRequest request, @QueryParam("type") String userType) {
        HttpSession session = request.getSession();

        User modelUser = (User) session.getAttribute("user");
        User modelOpponent = (User) session.getAttribute("opponent");
        Room room = (Room) session.getAttribute("room");
        User userInRoom = Objects.equals(userType, "opponent")
                ? room.getOpponentUser(modelUser)
                : room.getCurrentUser(modelUser);

        boolean isUpdateNeeded = server.getRoomStatus(room.getId()) == Room.RoomStatus.USER_UPDATE;
        boolean isGameFinished = server.getRoomStatus(room.getId()) == Room.RoomStatus.FINISHED;

        String response;
        if (isUpdateNeeded || isGameFinished) {
            response = Utils.addParamToJSON(userInRoom.toJSON(), "\"maxHp\": " + modelUser.getHp());
            response = Utils.addParamToJSON(response, "\"damage\": " + server.getLastTurnDamage(modelUser.getRoomId()));


            if (isUpdateNeeded && Objects.equals(userType, "user")) {
                room.setStatus(Room.RoomStatus.FIGHTING);
            }
            if (isGameFinished) {
                response = Utils.addParamToJSON(userInRoom.toJSON(), "\"isLastTurn\": true");
            }
            response = Utils.addParamToJSON(response, "\"opponent\": " + "\"" + modelOpponent.getUsername() + "\"");


        } else {
            response = "false";
        }
        return Response.status(Response.Status.OK).entity(response).build();
    }

    @GET
    @Path("/isNowCurrentUserTurn")
    public Response isNowCurrentUserTurn(@Context HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        return Response.status(Response.Status.OK).entity(Boolean.toString(server.isNowUsersTurn(user))).build();
    }

    @GET
    @Path("/winner")
    public Response getWinner(@Context HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        User winner = server.getWinner(user.getRoomId());
        return Response.status(Response.Status.OK).entity(winner).build();
    }

    @POST
    @Path("/updateUsersAfterGame")
    public Response updateUserAfterGame(@Context HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        User winner = server.getWinner(user.getRoomId());

        boolean isUserWinner = user.equals(winner);
        if (isUserWinner) {
            user.setStatus(User.UserStatus.WINNER);
        } else {
            user.setStatus(User.UserStatus.LOSER);
        }

        UserDaoMysql userDao = new UserDaoMysql();
        userDao.update(user);

        user.update(isUserWinner);
        session.setAttribute("isWinner", isUserWinner);

        return Response.status(Response.Status.OK).entity(Boolean.toString(isUserWinner)).build();
    }

}
