package com.learnspring.websocket;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
				title = "Spring WebSocket",
				version = "1.0.0",
				description = "This is demo application that demonstrates Spring Websocket",
				contact = @Contact(
						name = "Dev. Krishna Lagad",
						email = "support@gmail.com"
				),
				license = @License(
						name = "KrishnaL",
						url = "http://bit.ly/3x2D8oj"
				)

		)
)
public class WebsocketApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebsocketApplication.class, args);
	}

}
