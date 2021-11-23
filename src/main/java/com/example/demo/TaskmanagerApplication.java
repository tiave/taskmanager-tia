package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.demo.domain.Priority;
import com.example.demo.domain.PriorityRepository;
import com.example.demo.domain.Task;
import com.example.demo.domain.TaskRepository;
import com.example.demo.domain.User;
import com.example.demo.domain.UserRepository;

@SpringBootApplication
public class TaskmanagerApplication {
	
	private static final Logger log = LoggerFactory.getLogger(TaskmanagerApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(TaskmanagerApplication.class, args);
		
	}
		
		@Bean
		public CommandLineRunner taskDemo(TaskRepository trepo, PriorityRepository prepo, UserRepository urepo) {
			return (args) -> {
				
				if (urepo.findByUsername("user")  == null) {
				log.info("save a couple of tasks");
				
				
				prepo.save(new Priority("High"));
				prepo.save(new Priority("Medium"));
				prepo.save(new Priority("Low"));
			
				trepo.save(new Task("Kahville", "Strindberg", "9.11.2021", prepo.findByName("Medium").get(0)));
				trepo.save(new Task("Sulkapallo", "Talihalli", "11.11.2021", prepo.findByName("High").get(0)));	
				
				User user1 = new User("user", "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6", "USER");
				User user2 = new User("admin", "$2a$10$0MMwY.IQqpsVc1jC8u7IJ.2rT8b0Cd3b3sfIBGV2zfgnPGtT4r0.C", "ADMIN");
				urepo.save(user1);
				urepo.save(user2);
				
				log.info("fetch all tasks");
				for (Task task : trepo.findAll()) {
					log.info(task.toString());
				}
				}
				
			};
	}
}



