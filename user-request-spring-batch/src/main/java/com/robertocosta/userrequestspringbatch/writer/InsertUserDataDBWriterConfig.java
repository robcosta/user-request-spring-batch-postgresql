package com.robertocosta.userrequestspringbatch.writer;

import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.robertocosta.userrequestspringbatch.entities.User;

@Configuration
public class InsertUserDataDBWriterConfig {

	@Bean
	ItemWriter<User> insertUserDataDBWriter() {
		return users -> users.forEach(System.out::println);
	}
}
