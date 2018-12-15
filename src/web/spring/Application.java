package web.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.Filter;
import javax.servlet.MultipartConfigElement;
import javax.servlet.annotation.MultipartConfig;

import java.nio.charset.Charset;

import lombok.extern.log4j.Log4j;

@SpringBootApplication
@Configuration
@ComponentScan(basePackages = "web")
@EnableAutoConfiguration
@EnableWebMvc
@EnableJpaRepositories("web")
@MultipartConfig

@Log4j

public class Application extends SpringBootServletInitializer implements WebMvcConfigurer {

	public static final String LINE = "#####################################################################################################################";

	public Application() {
		log.info(LINE);
		log.info("Application");
		log.info(LINE);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(Application.class);
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		String funName = "addResourceHandlers(ResourceHandlerRegistry registry)";
		log.info(LINE);
		log.info(funName);
		log.info(LINE);

		// resource (css,js,img) resource location
		if (!registry.hasMappingForPattern("/rsc/**")) {
			registry.addResourceHandler("/rsc/**").addResourceLocations("file:/opt/tomcat/template/rsc/");
		}

		// Comtrade resource location
		if (!registry.hasMappingForPattern("/PSDR-XU/Comtrade/**")) {
			registry.addResourceHandler("/PSDR-XU/Comtrade/**")
					.addResourceLocations("file:/home/psdmts/PSDR-XU/Comtrade/");
		}
		
		// Fault resource location
		if (!registry.hasMappingForPattern("/PSDR-XU/Fault/**")) {
			registry.addResourceHandler("/PSDR-XU/Fault/**").addResourceLocations("file:/home/psdmts/PSDR-XU/Fault/");
		}				

		log.info(LINE);
		log.info("Done. " + funName);
		log.info(LINE);
	}

	@Bean
	public HttpMessageConverter<String> responseBodyConverter() {
		return new StringHttpMessageConverter(Charset.forName("UTF-8"));
	}

	@Order(Ordered.HIGHEST_PRECEDENCE)
	@Bean
	public Filter characterEncodingFilter() {
		CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
		characterEncodingFilter.setEncoding("UTF-8");
		characterEncodingFilter.setForceEncoding(true);
		return characterEncodingFilter;
	}

	@Bean
	public MultipartConfigElement multipartConfigElement() {
		MultipartConfigFactory factory = new MultipartConfigFactory();

		int maxUploadFileSize = 1_000_000_000;

		factory.setMaxFileSize(maxUploadFileSize);
		factory.setMaxRequestSize(maxUploadFileSize);

		return factory.createMultipartConfig();
	}

	@Bean
	public MultipartResolver multipartResolver() {
		return new StandardServletMultipartResolver();
	} 

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}