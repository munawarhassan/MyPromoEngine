package com.custompromoengine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.custompromoengine.model.PromoEngineConfig;

@EnableConfigurationProperties(PromoEngineConfig.class)
@SpringBootApplication
/**
 * @author SKHassan
 *
 */

public class PromoEngineApplication {

	public static void main(String[] args) {
		SpringApplication.run(PromoEngineApplication.class, args);
	}

}
