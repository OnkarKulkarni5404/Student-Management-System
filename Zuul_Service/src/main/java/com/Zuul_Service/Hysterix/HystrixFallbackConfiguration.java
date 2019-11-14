package com.Zuul_Service.Hysterix;

import org.springframework.cloud.netflix.zuul.filters.route.ZuulFallbackProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

public class HystrixFallbackConfiguration {
	@Bean
    public ZuulFallbackProvider zuulFallbackProvider() {
        return new ZuulFallbackProvider() {

        	@Override
			public String getRoute() {
				// TODO Auto-generated method stub
				return "STUDENT-FIND";
			}

			@Override  
			public ClientHttpResponse fallbackResponse() {
				// TODO Auto-generated method stub
				return new ClientHttpResponse(){

					@Override
					public InputStream getBody() throws IOException {
					
						return new ByteArrayInputStream("{\"factorA\":\"Sorry, Service is Down!\",\"factorB\":\"?\",\"id\":null}".getBytes());
					}

					@Override
					public HttpHeaders getHeaders() {
						HttpHeaders headers = new HttpHeaders();
                        headers.setContentType(MediaType.APPLICATION_JSON);
                        return headers;
					}

					@Override
					public void close() {
						// TODO Auto-generated method stub
						
					}

					@Override
					public int getRawStatusCode() throws IOException {
						 return HttpStatus.OK.value();
					}

					@Override
					public HttpStatus getStatusCode() throws IOException {
						return HttpStatus.OK;
					}

					@Override
					public String getStatusText() throws IOException {
						return HttpStatus.OK.toString();
					}
					
				};
			}

			
		
        	
        	
        };
}
}