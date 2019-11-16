package com.Zuul_Service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

import com.Zuul_Service.Filter.pre.PreFilter;

@EnableZuulProxy
@EnableDiscoveryClient
@SpringBootApplication
public class ZuulServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZuulServiceApplication.class, args);
	}
	
	@Bean
	  public PreFilter simpleFilter() {
	    return new PreFilter();
	  }

}
