package co.com.jineteapp.persistence.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;

@Configuration
@EnableR2dbcRepositories(basePackages = "co.com.jineteapp.persistence.repository")
public class R2dbcConfig {
}
