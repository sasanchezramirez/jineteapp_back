package co.com.jineteapp.apirest.mapper;

import co.com.jineteapp.apirest.dto.UserDto;
import co.com.jineteapp.model.User;
import org.mapstruct.Mapper;
import reactor.core.publisher.Mono;

@Mapper
public interface JineteappRestApiMapper {
    UserDto userToUserDto(User user);
    User userDtoToUser(UserDto userDto);
}
