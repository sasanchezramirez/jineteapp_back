package co.com.jineteapp.apirest.handler;

import co.com.jineteapp.apirest.dto.GenericResponseDto;
import co.com.jineteapp.apirest.dto.LoginDto;
import co.com.jineteapp.apirest.mapper.JineteappRestApiMapper;
import co.com.jineteapp.model.Login;
import co.com.jineteapp.usecase.AuthUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import reactor.util.Logger;
import reactor.util.Loggers;


@RequiredArgsConstructor
@Component
public class AuthHandler {
    public static final Logger log = Loggers.getLogger(AuthHandler.class.getName());
    private final JineteappRestApiMapper jineteappRestApiMapper;
    private final AuthUseCase authUseCase;

    public Mono<GenericResponseDto<Boolean>> login(LoginDto loginDto){
        log.debug("Initializing login");
        Login login = this.jineteappRestApiMapper.loginDtoToLogin(loginDto);
        return this.authUseCase.execute(login)
                .map(GenericResponseDto::new);
    }
}
