	package pt.arnaldocanelas.projetoapi.config;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.data.rest.RepositoryRestMvcAutoConfiguration;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebAutoConfiguration;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.data.web.config.EnableSpringDataWebSupport.PageSerializationMode;
import org.springframework.data.web.config.SpringDataWebSettings;

/**
 * https://github.com/spring-projects/spring-data-commons/issues/3108
 * 
 * in order to use spring-data-rest with hateos for repository rest resources alongside spring-data-web with {@link PageSerializationMode#VIA_DTO},
 * we enable {@link SpringDataWebProperties} and register {@link SpringDataWebSettings} manually
 * because {@link SpringDataWebAutoConfiguration} is not applied due to {@link RepositoryRestMvcAutoConfiguration} taking precedence
 * (@{@link AutoConfiguration}(after = {@link RepositoryRestMvcAutoConfiguration}.class), @{@link ConditionalOnMissingBean}({@link PageableHandlerMethodArgumentResolver}.class))
 *
 * @see SpringDataWebAutoConfiguration#springDataWebSettings()
 * @see RepositoryRestMvcConfiguration#pageableResolver()
 */
@Configuration
@EnableConfigurationProperties(SpringDataWebProperties.class)
public class DataRestConfig {

    @Bean
    SpringDataWebSettings springDataWebSettings(SpringDataWebProperties springDataWebProperties) {
        springDataWebProperties.getPageable().getSerializationMode();
		return new SpringDataWebSettings(PageSerializationMode.VIA_DTO);
    }

}