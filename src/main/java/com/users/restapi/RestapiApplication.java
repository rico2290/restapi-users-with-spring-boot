
package com.users.restapi;

import java.util.Random;
import java.util.stream.IntStream;

import com.users.restapi.models.User;
import com.users.restapi.repository.UserRepository;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;



@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class RestapiApplication {
	
	@Value("${application.name}")
	private String applicationName;

	public static void main(String[] args) {

		SpringApplication.run(RestapiApplication.class, args);
	}

	@Bean
	CommandLineRunner run(UserRepository userRepository){
		return new CommandLineRunner(){

			@Override
			public void run(String... args) throws Exception {
				// TODO Auto-generated method stub
				Random random = new Random();
				IntStream.rangeClosed(1, random.nextInt(100)).forEach(i ->{
					User user = new User();
					user.setName("user"+ i);
					user.setEmail("user"+ i + "@example.com");
					user.setPassword("pwd"+ random.nextInt(100) );
					userRepository.save(user);
				});
				System.out.println(applicationName);
				
				
			}
			
		};

	}

}
