package api.chocolates.rest.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


@Configuration
public class MvcConfig extends WebMvcConfigurerAdapter {
	
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		//building el bueno :
		registry.addViewController("/").setViewName("lista"); //nice
		registry.addViewController("/agrega").setViewName("agrega");  //nice

	}

}