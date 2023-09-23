package co.com.jineteapp.apirest.handler;

import co.com.jineteapp.apirest.dto.CreditCardDto;
import co.com.jineteapp.apirest.dto.GenericResponseDto;
import co.com.jineteapp.apirest.dto.SaveCreditCardDto;
import co.com.jineteapp.apirest.mapper.JineteappRestApiMapper;
import co.com.jineteapp.model.CreditCard;
import co.com.jineteapp.usecase.CreditCardUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import reactor.util.Logger;
import reactor.util.Loggers;

@Component
@RequiredArgsConstructor
public class CreditCardHandler {
    private static  final Logger log = Loggers.getLogger(CreditCardHandler.class.getName());
    private final CreditCardUseCase creditCardUseCase;
    private final JineteappRestApiMapper jineteappRestApiMapper;

    public Mono<GenericResponseDto<CreditCardDto>> getCreditCard(Integer id){
        log.debug("Initializing getCreditCard");
        return this.creditCardUseCase.getCreditCardUseCase(id)
                .map(this.jineteappRestApiMapper::creditCardToCreditCardDto)
                .map(GenericResponseDto::new);
    }

    public Mono<GenericResponseDto<Boolean>> saveCreditCard(SaveCreditCardDto saveCreditCardDto){
        CreditCard creditCard = this.jineteappRestApiMapper.saveCreditCardDtoToCreditCard(saveCreditCardDto);
        return this.creditCardUseCase.saveCreditCardUseCase(creditCard)
                .map(GenericResponseDto::new);
    }
}
