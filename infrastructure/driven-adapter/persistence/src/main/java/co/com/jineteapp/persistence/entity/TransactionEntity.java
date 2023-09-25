package co.com.jineteapp.persistence.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Setter
@Table(name = "transactions")
public class TransactionEntity {
    private Integer id;
    private Integer creditCardId;
    private Integer userId;
    private Integer typeOfJineteoId;
    private Integer typeOfTransactionId;
    private Integer amount;
    private String date;
    private String observation;
}
