package co.com.jineteapp.apirest.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class TransactionListDto {
    List<TransactionDto> transactionDtoList;

    public TransactionListDto(List<TransactionDto> transactions) {
        this.transactionDtoList = transactions;
    }
}
