package co.com.jineteapp.persistence.repository;

import co.com.jineteapp.persistence.entity.TypeOfJineteoEntity;
import org.springframework.data.r2dbc.repository.R2dbcRepository;

public interface JineteoTypesRepository extends R2dbcRepository<TypeOfJineteoEntity, Integer> {
}
