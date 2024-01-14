package co.com.jineteapp.persistence.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Setter
@Table(name = "types_of_jineteo")
public class TypeOfJineteoEntity {
    @Id
    public Integer id;
    public String description;
}
