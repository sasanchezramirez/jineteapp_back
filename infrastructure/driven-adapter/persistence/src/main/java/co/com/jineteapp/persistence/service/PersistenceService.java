package co.com.jineteapp.persistence.service;

import co.com.jineteapp.gateway.PersistenceGateway;
import co.com.jineteapp.model.CreditCard;
import co.com.jineteapp.model.User;
import co.com.jineteapp.persistence.entity.CreditCardEntity;
import co.com.jineteapp.persistence.mapper.PersistenceMapper;
import co.com.jineteapp.persistence.repository.CreditCardRepository;
import co.com.jineteapp.persistence.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.util.Logger;
import reactor.util.Loggers;

@Service
@AllArgsConstructor
public class PersistenceService implements PersistenceGateway {
    private static final Logger log = Loggers.getLogger(PersistenceService.class.getName());
    private final UserRepository userRepository;
    private final CreditCardRepository creditCardRepository;
    private final PersistenceMapper persistenceMapper;
    @Override
    public Mono<User> getUserById(Integer id) {
        return this.userRepository.findUserById(id)
                .map(this.persistenceMapper::userEntityToUser);
    }

    @Override
    public Mono<CreditCard> getCreditCardByUserId(Integer userId) {
        return this.creditCardRepository.findCreditCardByUserId(userId)
                .map(this.persistenceMapper::creditCardEntityToCreditCard);
    }

    @Override
    public Mono<Boolean> saveCreditCard(CreditCard creditCard) {
        CreditCardEntity creditCardEntity = this.persistenceMapper.creditCardToCreditCardEntity(creditCard);
        return this.creditCardRepository.save(creditCardEntity)
                .flatMap(savedCreditCard -> {
                    log.debug("Credit card successfully added");
                    return Mono.just(Boolean.TRUE);
                })
                .onErrorResume(e -> {
                    log.error("An error occurred while saving the credit card: "+ e );
                    return Mono.just(Boolean.FALSE);
                });
    }
}
