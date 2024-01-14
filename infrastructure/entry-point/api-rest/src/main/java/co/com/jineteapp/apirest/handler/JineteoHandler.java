package co.com.jineteapp.apirest.handler;

import co.com.jineteapp.apirest.dto.GenericResponseDto;
import co.com.jineteapp.apirest.dto.JineteoTypesDto;
import co.com.jineteapp.apirest.mapper.JineteappRestApiMapper;
import co.com.jineteapp.usecase.JineteoUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import reactor.util.Logger;
import reactor.util.Loggers;

@RequiredArgsConstructor
@Component
public class JineteoHandler {
    private static final Logger log = Loggers.getLogger(JineteoHandler.class.getName());
    private final JineteoUseCase jineteoUseCase;
    private final JineteappRestApiMapper jineteappRestApiMapper;

    public Mono<GenericResponseDto<JineteoTypesDto>> getJineteoTypes(){
        log.debug("Initializing getJineteoTypes");
        return this.jineteoUseCase.getJineteoTypes()
                .map(this.jineteappRestApiMapper::typeOfJineteoToTypeOfJineteoDto)
                .collectList()
                .map(JineteoTypesDto::new)
                .map(GenericResponseDto::new);
    }
}
