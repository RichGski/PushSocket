package com.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import com.system.PushUtil;

@Configuration
@EnableAsync
public class AssyncConfig {// implements AsyncConfigurer{ 
	
	@Bean
	public PushUtil pushUtil() {
		return new PushUtil();
	}
	
}