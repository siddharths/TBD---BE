/**
 * 
 */
package org.watchdog.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.rest.webmvc.config.RepositoryRestMvcConfiguration;

/**
 * Wires up and runs the app.
 * 
 * @author Vivek Ranjan
 */
@Configuration
@EnableJpaRepositories(basePackages = { "org.watchdog.service" })
@ComponentScan(basePackages = { "org.watchdog.controller", "org.watchdog.util" })
@EntityScan(basePackages = { "org.watchdog.domain" })
@Import(RepositoryRestMvcConfiguration.class)
@EnableAutoConfiguration
public class Application {

	/**
	 * Auto configures and starts the app.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
