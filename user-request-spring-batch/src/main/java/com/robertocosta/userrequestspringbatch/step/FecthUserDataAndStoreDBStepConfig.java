package com.robertocosta.userrequestspringbatch.step;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import com.robertocosta.userrequestspringbatch.dto.UserDTO;
import com.robertocosta.userrequestspringbatch.entities.User;

@Configuration
public class FecthUserDataAndStoreDBStepConfig {
	
	@Autowired
	private PlatformTransactionManager transactionManager;

	@Value("${chunkSize}")
	private int chunkSize;
	
	@Bean
	Step facthUserDataAndStoreDBStep(ItemReader<UserDTO> fetchUserDataReader,
			ItemProcessor<UserDTO, User> selectFieldsUserDataProcessor,
			ItemWriter<User> insertUserDataDBWriter,
			JobRepository jobRepository) {
		
		return new StepBuilder("facthUserDataAndStoreDBStep", jobRepository)
				.<UserDTO, User>chunk(chunkSize, transactionManager)
				.reader(fetchUserDataReader)
				.processor(selectFieldsUserDataProcessor)
				.writer(insertUserDataDBWriter)
				.build();
	}
}
