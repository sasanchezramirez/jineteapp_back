package co.com.jineteapp.usecase;

import co.com.jineteapp.gateway.PersistenceGateway;
import co.com.jineteapp.model.TypeOfJineteo;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.util.Logger;
import reactor.util.Loggers;

@RequiredArgsConstructor
public class JineteoUseCase {
    private static final Logger log = Loggers.getLogger(JineteoUseCase.class.getName());

    private final PersistenceGateway persistenceGateway;
    public Flux<TypeOfJineteo> getJineteoTypes(){
        log.debug("Initializing getJineteoTypes");
        return this.persistenceGateway.getTypesOfJineteo();
    }
}
