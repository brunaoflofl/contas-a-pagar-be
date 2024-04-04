package com.uece.contasapagar.data;

import com.github.javafaker.Faker;
import com.uece.contasapagar.entity.Account;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class DataGenerator {

    public static List<Account> generateFakeAccounts() {
        List<Account> accounts = new ArrayList<>();
        Faker faker = new Faker();

        for (int i = 0; i < 15; i++) {
            Account account = new Account();
            account.setName(faker.lorem().word());
            account.setValue(new BigDecimal(faker.number().randomDouble(2, 10, 1000)));
            account.setDueDate(getRandomDueDate(faker));
            account.setInterestRate(getRandomInterestRate());
            account.setPaid(i < 4);
            if (i >= 9 && i < 11) {
                account.setDueDate(LocalDate.now().minusDays(faker.number().numberBetween(1, 30)));
            }
            accounts.add(account);
        }

        return accounts;
    }

    private static LocalDate getRandomDueDate(Faker faker) {
        return faker.date().future(30, TimeUnit.DAYS).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    private static double getRandomInterestRate() {
        return ThreadLocalRandom.current().nextDouble(0.01, 0.1);
    }
}
