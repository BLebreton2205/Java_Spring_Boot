package com.test.webapp;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.*;

@Data
@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix="com.test.webapp")
public class CustomProperties {

	private String apiUrl;
}
