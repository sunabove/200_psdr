package web.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;
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
        templateResolver.setPrefix( "/opt/tomcat/template/html/" );
        templateResolver.setSuffix( "" );
        templateResolver.setCharacterEncoding("UTF-8");
        templateResolver.setTemplateMode( TemplateMode.HTML );
        templateResolver.setOrder( 0 );
        
        return templateResolver;
    }
    
    /*
    public SpringResourceTemplateResolver templateResolver() {
    	log.info( "templateResolver()" );
        SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
        templateResolver.setCacheable(false);
        templateResolver.setPrefix("classpath:/template/html/");
        templateResolver.setSuffix(".html");
        templateResolver.setCharacterEncoding("UTF-8");
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
        viewResolver.setTemplateEngine( this.templateEngine() );
        viewResolver.setOrder(0);
        viewResolver.setCharacterEncoding("UTF-8");
        
        return viewResolver;
    }
}