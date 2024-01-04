package co.com.jineteapp.apirest.handler;

import co.com.jineteapp.apirest.dto.GenericResponseDto;
import co.com.jineteapp.apirest.dto.UserDto;
import co.com.jineteapp.apirest.mapper.JineteappRestApiMapper;
import co.com.jineteapp.model.User;
import co.com.jineteapp.usecase.UserUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import reactor.util.Logger;
import reactor.util.Loggers;

@Component
@RequiredArgsConstructor
public class UserHandler {
    private static final Logger log = Loggers.getLogger(UserHandler.class.getName());
    private final UserUseCase userUsecase;
    private final JineteappRestApiMapper jineteappRestApiMapper;
    public Mono<GenericResponseDto<UserDto>> getUserById(Integer id){
        log.debug("Initializing getUser");
        return this.userUsecase.executeGetUser(id)
                .map(this.jineteappRestApiMapper::userToUserDto)
                .map(GenericResponseDto::new);
    }

    public Mono<GenericResponseDto<UserDto>> saveUser(UserDto userDto){
        log.debug("Initializing saveUser");
        User user = this.jineteappRestApiMapper.userDtoToUser(userDto);
        return this.userUsecase.executeSaveUser(user)
                .map(this.jineteappRestApiMapper::userToUserDto)
                .map(GenericResponseDto::new);
    }
}
