package co.com.kiire.app.config;

import co.com.kiire.gateway.contract.RestrictiveListGateway;
import co.com.kiire.gateway.contract.SaveUserGateway;
import co.com.kiire.usecase.SaveUserUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * UseCasesConfig
 * use cases instantiation
 */
@Configuration
public class UseCasesConfig {

    @Bean
    public SaveUserUseCase userUseCase(SaveUserGateway saveUserGateway, RestrictiveListGateway restrictiveListGateway) {
        return new SaveUserUseCase(saveUserGateway, restrictiveListGateway);
    }
}
