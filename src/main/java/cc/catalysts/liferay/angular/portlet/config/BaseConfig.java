package cc.catalysts.liferay.angular.portlet.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Imports ThymeleafConfig. Other BaseConfigs can be loaded here.
 */
@Configuration
@Import(ThymeleafConfig.class)
public class BaseConfig {
}
