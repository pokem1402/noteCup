package com.noteCup.config;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ITemplateResolver;

/** @formatter:off
 * ------------------------------------------------------
 * <Description>
 * ThymeLeaf Configuration
 * ------------------------------------------------------
 * @Project		: noteCup
 * @Package		: com.noteCup.config
 * @File		: ThymeLeafConfig.java
 * ------------------------------------------------------
 * @author		: 김원빈
 * @created		: 2021. 4. 27.
 * @type		: ThymeLeafConfig
 * @version		: 1.0
 * @formatter:on
 */
public class ThymeLeafConfig implements WebMvcConfigurer, ApplicationContextAware {
	
    @Autowired
    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws 
         BeansException {
        this.applicationContext = applicationContext;
    }

    @Bean
    public ViewResolver viewResolver(){
        ThymeleafViewResolver thymeleafViewResolver = new ThymeleafViewResolver();
        thymeleafViewResolver.setTemplateEngine(templateEngine());
        thymeleafViewResolver.setCharacterEncoding("UTF-8");
       return thymeleafViewResolver;
    }

    @Bean
    public SpringTemplateEngine templateEngine(){
       SpringTemplateEngine springTemplateEngine = new SpringTemplateEngine();
       springTemplateEngine.setEnableSpringELCompiler(true);
       springTemplateEngine.setTemplateResolver(templateResolver());
       return springTemplateEngine;
   }

   @Bean
   public ITemplateResolver templateResolver(){
       SpringResourceTemplateResolver springResourceTemplateResolver = new 
                        SpringResourceTemplateResolver();
       springResourceTemplateResolver.setApplicationContext(applicationContext);
       springResourceTemplateResolver.setPrefix("/WEB-INF/views/");
       springResourceTemplateResolver.setTemplateMode(TemplateMode.HTML);
       springResourceTemplateResolver.setSuffix(".html");
       springResourceTemplateResolver.setCharacterEncoding("UTF-8");
       return springResourceTemplateResolver;
  }

   @Override
   public void addResourceHandlers(ResourceHandlerRegistry registry) {
      registry
            .addResourceHandler("/resources/**")
            .addResourceLocations("/resources/");
   }
}