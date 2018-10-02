package cheetsheets.dk.resources;

import cheetsheets.dk.api.User;
import cheetsheets.dk.mapping.UserMapper;
import cheetsheets.dk.service.UserService;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/users")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {

    private final UserService userService;

    public UserResource(UserService userService) {
        this.userService = userService;
    }

    @GET
    public List<User> getUsers() {
        return UserMapper.INSTANCE.coreToApi(this.userService.getUsers());
    }

}
