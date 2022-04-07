package fr.deride.sbtreasuremap.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan(basePackages = "fr.deride.sbtreasuremap")
@PropertySource(value = "classpath:sb-tresor-map.properties", ignoreResourceNotFound = true)
public class ApplicationConfiguration  {

}