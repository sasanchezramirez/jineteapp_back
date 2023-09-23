package co.com.jineteapp.apirest.handler;

import co.com.jineteapp.apirest.dto.GenericResponseDto;
import co.com.jineteapp.apirest.dto.UserDto;
import co.com.jineteapp.apirest.mapper.JineteappRestApiMapper;
import co.com.jineteapp.usecase.GetUserUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import reactor.util.Logger;
import reactor.util.Loggers;

@Component
@RequiredArgsConstructor
public class GetUserHandler {
    private static final Logger log = Loggers.getLogger(GetUserHandler.class.getName());
    private final GetUserUseCase getUserUsecase;
    private final JineteappRestApiMapper jineteappRestApiMapper;
    public Mono<GenericResponseDto<UserDto>> getUserById(Integer id){
        log.debug("Initializing getUser");
        return this.getUserUsecase.execute(id)
                .map(this.jineteappRestApiMapper::userToUserDto)
                .map(GenericResponseDto::new);
    }
}
