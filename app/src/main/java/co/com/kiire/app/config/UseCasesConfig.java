package co.com.kiire.app.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

/**
 * UseCasesConfig
 * use cases instantiation
 */
@Configuration
@ComponentScan(basePackages = "co.com.kiire.usecase",
        includeFilters = {@ComponentScan.Filter(type = FilterType.REGEX, pattern = "^.+UseCase$")},
        useDefaultFilters = false)
public class UseCasesConfig {
}
