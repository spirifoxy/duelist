package duelist.spirifoxy.com.github.main;

import duelist.spirifoxy.com.github.model.Server;
import duelist.spirifoxy.com.github.model.User;

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
        User newPlayer = (User) session.getAttribute("user");
        server.connectUser(newPlayer);
        session.setAttribute("username", newPlayer.getUsername());

        return Response.status(Response.Status.OK).entity(session.getAttribute("username").toString()).build();
    }

    @GET
    @Path("/isRoomFilled")
    public Response isRoomFilled(@Context HttpServletRequest request) {

        return Response.status(Response.Status.OK).entity(Boolean.toString(server.isRoomFilled())).build();
    }



    /*

    @GET
    @Path("/")
    public Response isRoomFilled(@Context HttpServletRequest request) {

    }

    */
}
