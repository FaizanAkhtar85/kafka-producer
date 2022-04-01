package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class KafkaPublisherApplication {

	@Autowired
	public KafkaTemplate< String, Object> template;
	
	private String topic = "topic1";
	
	@GetMapping("/publish/{name}")
	public String publishMessage(@PathVariable String name) {
		template.send(topic, "Hi {}",name+" Welcome to Kafka implementatiom");
	return "Data Published";	
	}
	
	@GetMapping("/publishJson")
	public String publishJsonMessage() {
		User user= new User(101,"Faizan",new String[] {"Bareilly","H No 103","khushboo enclave"});
		template.send(topic, user);
	return "Data Published";	
	}
	
	public static void main(String[] args) {
		SpringApplication.run(KafkaPublisherApplication.class, args);
	}

}
