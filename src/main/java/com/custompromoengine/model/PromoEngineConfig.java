package com.custompromoengine.model;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;


@ConfigurationProperties
public class PromoEngineConfig {	
	
	private String greetProp;
	
	public String getGreetProp() {
		return greetProp;
	}
	public void setGreetProp(String greetProp) {
		this.greetProp = greetProp;
	}
	
   
}
