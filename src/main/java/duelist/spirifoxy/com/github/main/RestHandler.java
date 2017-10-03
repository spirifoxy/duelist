package duelist.spirifoxy.com.github.main;

import duelist.spirifoxy.com.github.model.Room;
import duelist.spirifoxy.com.github.model.Server;
import duelist.spirifoxy.com.github.model.User;
import duelist.spirifoxy.com.github.utils.Utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

@Path("/")
public class RestHandler {
    private Server server = Server.getInstance();

    @POST
    @Path("/onConnect/")
    public Response onConnect(@Context HttpServletRequest request) {

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        server.connectUser(user);
        session.setAttribute("username", user.getUsername());

        return Response.status(Response.Status.OK).entity(session.getAttribute("username").toString()).build();
    }

    @GET
    @Path("/isRoomFilled")
    public Response isRoomFilled(@Context HttpServletRequest request) {

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        boolean isRoomFilled = server.isRoomFilled();
        if (isRoomFilled) {
            Room room = server.getLastRoom();
            User opponent = room.getOpponentUser(user);
            session.setAttribute("room", room);
            session.setAttribute("opponent", opponent);
        }

        return Response.status(Response.Status.OK).entity(Boolean.toString(isRoomFilled)).build();
    }

    @GET
    @Path("/timeBeforeDuel")
    public Response getTimeBeforeDuel(@Context HttpServletRequest request) {
        HttpSession session = request.getSession();

        int time = server.getTimeBeforeDuel();

        return Response.status(Response.Status.OK).entity(Integer.toString(time)).build();
    }

    @POST
    @Path("/attack")
    public Response onAttack(@Context HttpServletRequest request) {

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        Boolean isAttackProcessed = server.processAttack(user);

        return Response.status(Response.Status.OK).entity(Boolean.toString(isAttackProcessed)).build();
    }

    @GET
    @Path("/updateCurrentUserInfo")
    public Response updateCurrentUserInfo(@Context HttpServletRequest request) {
        HttpSession session = request.getSession();
        User modelUser = (User) session.getAttribute("user");
        User userInRoom = ((Room) session.getAttribute("room")).getCurrentUser(modelUser);

        boolean isUpdateNeeded = server.getRoomStatus() == Room.RoomStatus.USER_UPDATE;

        //add maxHp parameter to response
        String extraParam = "\"maxHp\": " + modelUser.getHp();
        String userJSONInfo = Utils.addParamToJSON(userInRoom.toJSON(), extraParam);

        return Response.status(Response.Status.OK).entity(isUpdateNeeded ? userJSONInfo : "false").build();
    }


    @GET
    @Path("/updateOpponentUserInfo")
    public Response updateOpponentUserInfo(@Context HttpServletRequest request) {
        HttpSession session = request.getSession();

        User modelUser = (User) session.getAttribute("user");
        User opponentInRoom = ((Room) session.getAttribute("room")).getOpponentUser(modelUser);

        boolean isUpdateNeeded = server.getRoomStatus() == Room.RoomStatus.USER_UPDATE;

        //add maxHp parameter to response
        String extraParam = "\"maxHp\": " + modelUser.getHp();
        String opponentJSONInfo = Utils.addParamToJSON(opponentInRoom.toJSON(), extraParam);

        return Response.status(Response.Status.OK).entity(isUpdateNeeded ? opponentJSONInfo : "false").build();
    }

    /*

    @GET
    @Path("/")
    public Response isRoomFilled(@Context HttpServletRequest request) {

    }

    */
}
