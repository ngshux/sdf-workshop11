package com.vttp2022.workshop11;

import org.springframework.boot.DefaultApplicationArguments;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.filter.CommonsRequestLoggingFilter;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SpringBootApplication
public class Workshop11Application {

	private static final Logger logger = LoggerFactory.getLogger(Workshop11Application.class);
	private static final String DEFAULT_PORT_NUMBER = "3000";
	@Bean
	public CommonsRequestLoggingFilter loggingFilter(){
		CommonsRequestLoggingFilter loggerFilter = new CommonsRequestLoggingFilter();
		loggerFilter.setIncludeClientInfo(true);
		loggerFilter.setIncludeHeaders(true);
		loggerFilter.setIncludeQueryString(true);
		loggerFilter.setIncludePayload(true);
		return loggerFilter;
	}
	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(Workshop11Application.class);
		logger.info("Web App");

		DefaultApplicationArguments appArgs = new DefaultApplicationArguments(args);
		List opsVal = appArgs.getOptionValues("port");
		logger.info("opsVal > " + opsVal);

		String portNum;
		if(opsVal == null || opsVal.get(0) == null){
			portNum = System.getenv("PORT");
			if(portNum == null)
			portNum = DEFAULT_PORT_NUMBER;
		} else{
			portNum = (String) opsVal.get(0);
		}

		if(portNum != null){
			app.setDefaultProperties(Collections.singletonMap("server.port",portNum));
		}
		app.run(args);
	}

}
