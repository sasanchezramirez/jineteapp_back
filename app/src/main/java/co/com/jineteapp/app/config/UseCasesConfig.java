package co.com.jineteapp.app.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

/**
 * UseCasesConfig
 * use cases instantiation
 */
@Configuration
@ComponentScan(basePackages = "co.com.jineteapp.usecase",
        includeFilters = {@ComponentScan.Filter(type = FilterType.REGEX, pattern = "^.+UseCase$")},
        useDefaultFilters = false)
public class UseCasesConfig {
}
