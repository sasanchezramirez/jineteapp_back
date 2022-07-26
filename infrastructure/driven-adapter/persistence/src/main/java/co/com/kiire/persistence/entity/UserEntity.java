package co.com.kiire.persistence.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

/**
 * Entidad que va a representar la tabla de Usuarios en la base de datos
 */
@Getter
@Setter
@NoArgsConstructor
@Table("users")
public class UserEntity {
    @Id
    private Long id;
    private String name;
    private String code;
    private String password;

}
