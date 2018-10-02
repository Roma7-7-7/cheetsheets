package cheetsheets.dk.mapping;

import cheetsheets.dk.api.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.stream.Collectors;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "email", target = "email"),
            @Mapping(source = "password", target = "password"),
            @Mapping(source = "name", target = "name"),
            @Mapping(source = "lastName", target = "lastName")
    })
    User coreToApi(cheetsheets.dk.core.User user);

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "email", target = "email"),
            @Mapping(source = "password", target = "password"),
            @Mapping(source = "name", target = "name"),
            @Mapping(source = "lastName", target = "lastName")
    })
    cheetsheets.dk.core.User apiToCore(User user);

    default List<User> coreToApi(List<cheetsheets.dk.core.User> users) {
        if (users == null) {
            return null;
        }
        return users.stream().map(this::coreToApi).collect(Collectors.toList());
    }

    default List<cheetsheets.dk.core.User> apiToCore(List<User> users) {
        if (users == null) {
            return null;
        }
        return users.stream().map(this::apiToCore).collect(Collectors.toList());
    }

}
