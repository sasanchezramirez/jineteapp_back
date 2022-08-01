package co.com.kiire.apirest.handler;

import co.com.kiire.apirest.dto.GenericResponseDTO;
import co.com.kiire.apirest.dto.UserDTO;
import co.com.kiire.apirest.mapper.UserApiRestMapper;
import co.com.kiire.model.util.ResponseCode;
import co.com.kiire.usecase.GetUsersUseCase;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;
import reactor.core.publisher.Mono;
import reactor.util.Logger;
import reactor.util.Loggers;

import java.util.List;

@Component
@RequiredArgsConstructor
public class getUsersHandler {

    private static final Logger log = Loggers.getLogger(getUsersHandler.class.getName());

    private final GetUsersUseCase getUsersUseCase;
    private final UserApiRestMapper userApiRestMapper;

    public Mono<GenericResponseDTO<List<UserDTO>>> getUsers(
            @Parameter(name = "name", description = "Nombre de los usuarios", required = true, in = ParameterIn.QUERY) @RequestParam String name) {
        ErrorHandler<List<UserDTO>> errorHandler = new ErrorHandler<>();
        return errorHandler.addErrors(Mono.just(name)
                .doOnSuccess(request -> log.debug("Init saveUser with request {}", request))
                .flatMapMany(this.getUsersUseCase::execute)
                .map(this.userApiRestMapper::userToUserDto)
                .collectList()
                .map(userDto -> new GenericResponseDTO<>(ResponseCode.KAR001, userDto))
                .doOnSuccess(response -> log.debug("Finish saveUser with response {}", response)), "saveUser");
    }

}
