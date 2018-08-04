package web.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.FileTemplateResolver;

import lombok.extern.log4j.Log4j;

@Configuration
@Log4j
public class ThymeleafConfig {
    @Bean
    public FileTemplateResolver templateResolver() {
    	log.info( "templateResolver()" );
        var templateResolver = new FileTemplateResolver();
        templateResolver.setCacheable(false);
        templateResolver.setPrefix("/opt/tomcat/template/html/");
        templateResolver.setSuffix(".html");
        return templateResolver;
    }
    
    /*
    public SpringResourceTemplateResolver templateResolver() {
    	log.info( "templateResolver()" );
        SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
        templateResolver.setCacheable(false);
        templateResolver.setPrefix("classpath:/template/html/");
        templateResolver.setSuffix(".html");
        return templateResolver;
    }
    */

    @Bean
    public SpringTemplateEngine templateEngine() {
        SpringTemplateEngine springTemplateEngine = new SpringTemplateEngine();
        springTemplateEngine.addTemplateResolver(templateResolver());
        return springTemplateEngine;
    }

    @Bean
    public ThymeleafViewResolver viewResolver() {
        ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
        viewResolver.setTemplateEngine(templateEngine());
        viewResolver.setOrder(1);
        return viewResolver;
    }
}