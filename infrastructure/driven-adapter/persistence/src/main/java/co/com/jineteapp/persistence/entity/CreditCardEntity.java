package co.com.jineteapp.persistence.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;


@Getter
@Setter
@Table(name = "credit_cards")
public class CreditCardEntity {
    @Id
    private Integer id;
    private Integer userId;
    private String name;
    private Integer availability;
    private Integer balance;
    private String datelineToPay;
}
