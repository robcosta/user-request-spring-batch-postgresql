package com.robertocosta.userrequestspringbatch.step;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import com.robertocosta.userrequestspringbatch.dto.UserDTO;

@Configuration
public class FecthUserDataAndStoreDBStepConfig {
	
	@Autowired
	private PlatformTransactionManager transactionManager;

	@Value("${chunckSize)")
	private int chunkSize;
	
	@Bean
	public Step facthUserDataAndStoreDBStep(ItemReader<UserDTO> fetchUserDataReader,JobRepository jobRepository) {
		
		return new StepBuilder("facthUserDataAndStoreDBStep", jobRepository)
				.<UserDTO, UserDTO>chunk(chunkSize, transactionManager)
				.reader(fetchUserDataReader)
				.build();
	}
}
