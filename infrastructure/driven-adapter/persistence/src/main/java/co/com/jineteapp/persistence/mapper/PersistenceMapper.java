package co.com.jineteapp.persistence.mapper;

import co.com.jineteapp.model.User;
import co.com.jineteapp.persistence.entity.UserEntity;
import org.mapstruct.Mapper;

@Mapper
public interface PersistenceMapper {
    User userEntityToUser(UserEntity userEntity);
}
