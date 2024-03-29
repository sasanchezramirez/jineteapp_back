package co.com.jineteapp.apirest.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransactionDto {
    private Integer id;
    private Integer creditCardId;
    private Integer userId;
    private Integer typeOfJineteoId;
    private Integer typeOfTransactionId;
    private Integer amount;
    private String date;
    private String observation;
    private Integer losses;
}
