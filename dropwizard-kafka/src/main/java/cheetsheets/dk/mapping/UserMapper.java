package cheetsheets.dk.mapping;

import cheetsheets.dk.api.User;
import cheetsheets.dk.core.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "email", target = "email"),
            @Mapping(source = "password", target = "password"),
            @Mapping(source = "name", target = "name"),
            @Mapping(source = "lastName", target = "lastName"),
            @Mapping(source = "roles", target = "roles")
    })
    User coresToApis(cheetsheets.dk.core.User user);

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "email", target = "email"),
            @Mapping(source = "password", target = "password"),
            @Mapping(source = "name", target = "name"),
            @Mapping(source = "lastName", target = "lastName"),
            @Mapping(source = "roles", target = "roles")
    })
    cheetsheets.dk.core.User apisToCores(User user);

    List<User> coresToApis(List<cheetsheets.dk.core.User> users);

    Role stringToRoleName(String name);

    default String roleToName(Role role) {
        if (role == null) {
            return null;
        }
        return role.getName();
    }

}
