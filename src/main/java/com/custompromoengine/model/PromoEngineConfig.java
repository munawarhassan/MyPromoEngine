package com.custompromoengine.model;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;


@ConfigurationProperties
public class PromoEngineConfig {	
	
	private String firstpromo= "PROMO_A";
	private String secondpromo= "PROMO_B";
	private String thirdpromo= "PROMO_CD";
	private String forthpromo= "PROMO_ALL";
	public String getFirstpromo() {
		return firstpromo;
	}
	public void setFirstpromo(String firstpromo) {
		this.firstpromo = firstpromo;
	}
	public String getSecondpromo() {
		return secondpromo;
	}
	public void setSecondpromo(String secondpromo) {
		this.secondpromo = secondpromo;
	}
	public String getThirdpromo() {
		return thirdpromo;
	}
	public void setThirdpromo(String thirdpromo) {
		this.thirdpromo = thirdpromo;
	}
	public String getForthpromo() {
		return forthpromo;
	}
	public void setForthpromo(String forthpromo) {
		this.forthpromo = forthpromo;
	}
	
   
}
