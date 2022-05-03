package com.smu.forum;

import com.smu.forum.domain.Property;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({Property.class})
public class ForumApplication {
	public static void main(String[] args) {
		SpringApplication.run(ForumApplication.class, args);
	}
}
