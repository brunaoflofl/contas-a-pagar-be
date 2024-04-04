package com.uece.contasapagar;

import com.uece.contasapagar.data.DataGenerator;
import com.uece.contasapagar.entity.Account;
import com.uece.contasapagar.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class ContasAPagarApplication implements CommandLineRunner {

	@Value("${generate.fake.data}")
	private boolean generateFakeData;

	@Autowired
	private AccountRepository accountRepository;

	public static void main(String[] args) {
		SpringApplication.run(ContasAPagarApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		if (generateFakeData) {
			List<Account> fakeAccounts = DataGenerator.generateFakeAccounts();
			accountRepository.saveAll(fakeAccounts);
		}
	}
}
