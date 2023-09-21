package co.com.jineteapp.persistence.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.relational.core.mapping.Table;
@Getter
@Setter
@Table(name = "users")
public class UserEntity {
    private Integer id;
    private String name;
    private String email;
}
