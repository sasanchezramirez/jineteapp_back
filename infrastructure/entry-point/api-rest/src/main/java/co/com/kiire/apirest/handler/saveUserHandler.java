package co.com.kiire.apirest.handler;

import co.com.kiire.apirest.dto.GenericResponseDTO;
import co.com.kiire.apirest.dto.SaveUserDto;
import co.com.kiire.apirest.dto.UserDTO;
import co.com.kiire.apirest.mapper.UserApiRestMapper;
import co.com.kiire.model.util.ResponseCode;
import co.com.kiire.usecase.SaveUserUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import reactor.core.publisher.Mono;
import reactor.util.Logger;
import reactor.util.Loggers;

@Component
@RequiredArgsConstructor
public class saveUserHandler {

    private static final Logger log = Loggers.getLogger(saveUserHandler.class.getName());

    private final SaveUserUseCase userUseCase;
    private final UserApiRestMapper userApiRestMapper;

    public Mono<GenericResponseDTO<UserDTO>> saveUser(@RequestBody SaveUserDto saveUserDto) {
        ErrorHandler<UserDTO> errorHandler = new ErrorHandler<>();
        return errorHandler.addErrors(Mono.just(saveUserDto)
                .doOnSuccess(request -> log.debug("Init saveUser with request {}", request))
                .map(this.userApiRestMapper::saveUserDtoToUser)
                .flatMap(this.userUseCase::execute)
                .map(this.userApiRestMapper::userToUserDto)
                .map(userDto -> new GenericResponseDTO<>(ResponseCode.KAR001, userDto))
                .doOnSuccess(response -> log.debug("Finish saveUser with response {}", response)), "saveUser");
    }

}
