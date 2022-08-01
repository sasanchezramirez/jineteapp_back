package co.com.kiire.apirest.mapper;

import co.com.kiire.apirest.dto.SaveUserDto;
import co.com.kiire.apirest.dto.UserDTO;
import co.com.kiire.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface UserApiRestMapper {

    @Mapping(target = "id", ignore = true)
    User saveUserDtoToUser(SaveUserDto saveUserDto);

    UserDTO userToUserDto(User user);
}
