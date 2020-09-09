package com.custompromoengine.model;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;
import lombok.Setter;

@Setter @Getter
@ConfigurationProperties
public class PromoEngineConfig {	
	
	private String firstpromo= "PROMO_A";
	private String secondpromo= "PROMO_B";
	private String thirdpromo= "PROMO_CD";
	private String forthpromo= "PROMO_ALL";
}
