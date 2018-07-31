package web.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import lombok.extern.log4j.Log4j;

@SpringBootApplication
@Configuration
@ComponentScan( basePackages = "web" )
@EnableAutoConfiguration
@EnableWebMvc
@EnableJpaRepositories( "web" )
@Log4j

public class Application extends SpringBootServletInitializer implements WebMvcConfigurer { 
	
	private static final String[] CLASSPATH_RESOURCE_LOCATIONS = {
			"classpath:/META-INF/resources/", "classpath:/resources/",
			"classpath:/templates/", "classpath:/public/" };
	
	public Application() {
		log.info( "###################################" );
		log.info( "Application" );
		log.info( "###################################" );
	}
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(Application.class);
	}
    
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
    	log.info( "###################################" );
    	log.info( "addResourceHandlers(ResourceHandlerRegistry registry)" );
    	log.info( "###################################" );
    	
    	if (!registry.hasMappingForPattern("/webjars/**")) {
    		registry.addResourceHandler("/webjars/**").addResourceLocations(
    				"classpath:/META-INF/resources/webjars/");
    	}
    	if (!registry.hasMappingForPattern("/**")) {
    		registry.addResourceHandler("/**").addResourceLocations(
    				CLASSPATH_RESOURCE_LOCATIONS);
    	}
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}