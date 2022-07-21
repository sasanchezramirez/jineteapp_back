package co.com.kiire.apirest.util.mapper;

import co.com.kiire.apirest.dto.SaveUserDto;
import co.com.kiire.apirest.dto.UserDTO;
import co.com.kiire.model.User;
import lombok.experimental.UtilityClass;

@UtilityClass
public class UserMapper {

    public static User saveUserDtoToUser(SaveUserDto saveUserDto) {
        User user = new User();
        user.setCode(saveUserDto.getCode());
        user.setName(saveUserDto.getName());
        user.setPassword(saveUserDto.getPassword());
        return user;
    }

    public static UserDTO userToUserDto(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setCode(user.getCode());
        userDTO.setName(user.getName());
        userDTO.setPassword(user.getPassword());
        return userDTO;
    }
}
