package org.watchdog.boot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mangofactory.swagger.configuration.SpringSwaggerConfig;
import com.mangofactory.swagger.plugin.EnableSwagger;
import com.mangofactory.swagger.plugin.SwaggerSpringMvcPlugin;
import com.wordnik.swagger.model.ApiInfo;

/**
 * Configuration for Swagger. Automatic API documentation creation.
 * 
 * @author Vivek Ranjan
 *
 */
@Configuration
@EnableSwagger
public class SwaggerConfig {
	private SpringSwaggerConfig springSwaggerConfig;

	@Autowired
	public void setSpringSwaggerConfig(SpringSwaggerConfig springSwaggerConfig) {
		this.springSwaggerConfig = springSwaggerConfig;
	}

	@Bean
	public SwaggerSpringMvcPlugin customImplementation() {
		return new SwaggerSpringMvcPlugin(this.springSwaggerConfig).apiInfo(
				apiInfo()).includePatterns("/user.*");
	}

	/**
	 * API Info as it appears on the swagger-ui page
	 */
	private ApiInfo apiInfo() {
		ApiInfo apiInfo = new ApiInfo("Watchdog API",
				"API for Watchdog REST endpoints",
				"Watchdog API terms of service", "placeholder@gmail.com",
				"Watchdog API Licence Type", "Watchdog API License URL");
		return apiInfo;
	}
}
