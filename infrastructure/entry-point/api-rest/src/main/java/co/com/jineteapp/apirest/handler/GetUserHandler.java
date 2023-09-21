package co.com.jineteapp.apirest.handler;

import co.com.jineteapp.apirest.dto.GenericResponseDto;
import co.com.jineteapp.apirest.dto.UserDto;
import co.com.jineteapp.apirest.mapper.JineteappRestApiMapper;
import co.com.jineteapp.usecase.GetUserUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class GetUserHandler {
    private final GetUserUseCase getUserUsecase;
    private final JineteappRestApiMapper jineteappRestApiMapper;
    public Mono<GenericResponseDto<UserDto>> getUserById(Integer id){
        return this.getUserUsecase.execute(id)
                .map(this.jineteappRestApiMapper::userToUserDto)
                .map(GenericResponseDto::new);
    }
}
