package com.cg.mobilebilling.springwebconfig;



import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.http.MediaType;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.ResourceBundleViewResolver;
@Configuration
public class BillingConfig implements WebMvcConfigurer{	
	@Override
	public Validator getValidator() {
		LocalValidatorFactoryBean bean=new LocalValidatorFactoryBean();
		bean.setValidationMessageSource(getMessageSource());
		return bean;
	}
	
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/images/**").addResourceLocations("classpath:/static/images/");
		//WebMvcConfigurer.super.addResourceHandlers(registry);
	}
	
	@Bean
	public ResourceBundleViewResolver getResourceBundleViewResolver() {
		ResourceBundleViewResolver rbvr = new ResourceBundleViewResolver();
		rbvr.setBasename("pdf");
		return rbvr;
	}
	
	@Bean
	public InternalResourceViewResolver getInternalResourceViewResolver() {
		InternalResourceViewResolver ref=new InternalResourceViewResolver();
		ref.setPrefix("/views/");
		ref.setSuffix(".jsp");
		return ref;
	}
	
	@Bean
	public MessageSource getMessageSource() {
		ReloadableResourceBundleMessageSource messageSource=new ReloadableResourceBundleMessageSource();
		messageSource.setBasename("classpath:messages");
		messageSource.setDefaultEncoding("UTF-8");
		return messageSource;	
	}
}
