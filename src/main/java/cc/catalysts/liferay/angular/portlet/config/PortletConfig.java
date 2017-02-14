package cc.catalysts.liferay.angular.portlet.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Contains the basic configuration of the portlet.
 */
@Configuration
@ComponentScan("cc.catalysts.liferay.angular.portlet.controller")
public class PortletConfig {
}