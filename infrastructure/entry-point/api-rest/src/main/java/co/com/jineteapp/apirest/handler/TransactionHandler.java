package co.com.jineteapp.apirest.handler;

import co.com.jineteapp.apirest.dto.GenericResponseDto;
import co.com.jineteapp.apirest.dto.SaveTransactionDto;
import co.com.jineteapp.apirest.dto.TransactionDto;
import co.com.jineteapp.apirest.dto.TransactionListDto;
import co.com.jineteapp.apirest.mapper.JineteappRestApiMapper;
import co.com.jineteapp.model.Transaction;
import co.com.jineteapp.usecase.TransactionUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.Logger;
import reactor.util.Loggers;
@Component
@RequiredArgsConstructor
public class TransactionHandler {
    private static final Logger log = Loggers.getLogger(TransactionHandler.class.getName());
    private final TransactionUseCase transactionUseCase;
    private final JineteappRestApiMapper jineteappRestApiMapper;

    public Mono<GenericResponseDto<Boolean>> saveTransaction(SaveTransactionDto saveTransactionDto){
        log.debug("Initializing saveTransaction");
        Transaction transaction = this.jineteappRestApiMapper.saveTransactionDtoToTransaction(saveTransactionDto);
        return this.transactionUseCase.saveTransactionUseCase(transaction)
                .map(GenericResponseDto::new);
    }
    public Mono<GenericResponseDto<TransactionListDto>> getTransactionByUserId(Integer userId){
        log.debug("Initializing getTransactionByUserId");
        return  this.transactionUseCase.getTransactionByUserId(userId)
                .map(this.jineteappRestApiMapper::transactionToTransactionDto)
                .collectList()
                .map(TransactionListDto::new)
                .map(GenericResponseDto::new);
    }
    public Flux<GenericResponseDto<TransactionDto>> getTransactionByCreditCardId(Integer creditCardId){
        log.debug("Initializing getTransactionByCreditCardId");
        return  this.transactionUseCase.getTransactionCreditCardId(creditCardId)
                .map(this.jineteappRestApiMapper::transactionToTransactionDto)
                .map(GenericResponseDto::new);
    }
}
