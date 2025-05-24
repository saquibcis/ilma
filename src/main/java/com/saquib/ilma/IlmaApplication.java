package com.saquib.ilma;

import org.springframework.ai.model.openai.autoconfigure.OpenAiChatAutoConfiguration;
import org.springframework.ai.model.openai.autoconfigure.OpenAiImageAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoConfiguration(exclude = {
	    OpenAiChatAutoConfiguration.class,
	    OpenAiImageAutoConfiguration.class
	})
public class IlmaApplication {

	public static void main(String[] args) {
		SpringApplication.run(IlmaApplication.class, args);
	}

}
