package co.com.kiire.persistence.mapper;

import co.com.kiire.model.User;
import co.com.kiire.persistence.entity.UserEntity;
import org.mapstruct.Mapper;

@Mapper
public interface UserPersistenceMapper {

    UserEntity userToUserDto(User user);

    User userDtoToUser(UserEntity userEntity);
}
