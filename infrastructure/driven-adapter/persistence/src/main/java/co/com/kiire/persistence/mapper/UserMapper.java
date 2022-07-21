package co.com.kiire.persistence.mapper;

import co.com.kiire.model.User;
import co.com.kiire.persistence.entity.UserEntity;
import lombok.experimental.UtilityClass;

@UtilityClass
public class UserMapper {

    public static UserEntity userToUserDto(User user) {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(user.getId());
        userEntity.setCode(user.getCode());
        userEntity.setName(user.getName());
        userEntity.setPassword(user.getPassword());
        return userEntity;
    }

    public static User userDtoToUser(UserEntity userEntity) {
        User user = new User();
        user.setId(userEntity.getId());
        user.setCode(userEntity.getCode());
        user.setName(userEntity.getName());
        user.setPassword(userEntity.getPassword());
        return user;
    }
}
