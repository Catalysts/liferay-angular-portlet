package cc.catalysts.liferay.angular.portlet.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;

/**
 * Thymeleaf Configuration.
 */
@Configuration
public class ThymeleafConfig {

    private static final String SYSTEM_PROPERTY_THYMELEAF_CACHEABLE = "thymeleaf.cacheable";

    /**
     * ViewResolver bean. Configures a ThymeleafViewResolver.
     *
     * @param templateEngine
     * @return
     */
    @Bean
    public ViewResolver viewResolver(SpringTemplateEngine templateEngine) {
        ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
        viewResolver.setTemplateEngine(templateEngine);
        return viewResolver;
    }

    /**
     * SpringResourceTemplateResolver bean. Configures the thymeleaf-template folder.
     *
     * @return
     */
    @Bean
    public SpringResourceTemplateResolver templateResolver() {
        SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
        templateResolver.setTemplateMode("HTML5");
        templateResolver.setPrefix("/WEB-INF/thymeleaf/templates/");
        templateResolver.setSuffix(".html");
        templateResolver.setCharacterEncoding("UTF-8");
        templateResolver.setCacheable(isThymeleafCacheable());
        return templateResolver;
    }

    /**
     * SpringTemplateEngine bean.
     *
     * @param templateResolver
     * @return
     */
    @Bean
    public SpringTemplateEngine templateEngine(SpringResourceTemplateResolver templateResolver) {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);
        return templateEngine;
    }

    private boolean isThymeleafCacheable() {
        String thymeleafCacheable = System.getProperty(SYSTEM_PROPERTY_THYMELEAF_CACHEABLE);
        // always cacheable (true), expect the string 'false'
        return thymeleafCacheable == null || !thymeleafCacheable.equalsIgnoreCase(Boolean.FALSE.toString());
    }
}
