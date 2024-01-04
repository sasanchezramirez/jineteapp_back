package co.com.jineteapp.persistence.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
@Getter
@Setter
@Table(name = "users")
public class UserEntity {
    @Id
    private Integer id;
    private String name;
    private String username;
    private String email;
    private String password;
}
