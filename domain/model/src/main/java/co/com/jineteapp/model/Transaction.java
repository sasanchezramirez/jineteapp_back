package co.com.jineteapp.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Transaction {
    private Integer id;
    private Integer creditCardId;
    private Integer userId;
    private Integer typeOfJineteoId;
    private Integer typeOfTransactionId;
    private Integer amount;
    private String date;
    private String observation;
}
