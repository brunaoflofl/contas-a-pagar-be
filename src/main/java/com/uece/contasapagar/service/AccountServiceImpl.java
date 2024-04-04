package com.uece.contasapagar.service;

import com.uece.contasapagar.dto.AccountRecordDTO;
import com.uece.contasapagar.entity.Account;
import com.uece.contasapagar.repository.AccountRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl  implements AccountService {
    @Autowired
    private AccountRepository accountRepository;

    @Override
    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    @Override
    public List<Account> getAllPaidAccounts() {
        List<Account> allAccounts = accountRepository.findAll();
        return allAccounts.stream()
                .filter(Account::isPaid)
                .collect(Collectors.toList());
    }

    @Override
    public List<Account> getAllNotPaidAccounts() {
        List<Account> allAccounts = accountRepository.findAll();
        return allAccounts.stream()
                .filter(account -> !account.isPaid())
                .collect(Collectors.toList());
    }

    @Override
    public Account getAccountById(Long id) {
        return accountRepository.findById(id).orElse(null);
    }

    @Override
    public Account saveAccount(AccountRecordDTO accountRecordDto) {
        var account = new Account();
        BeanUtils.copyProperties(accountRecordDto, account);
        return accountRepository.save(account);
    }

    @Override
    public void deleteAccount(Long id) {
        accountRepository.deleteById(id);
    }

    @Override
    public Account updateAccount(Long id, AccountRecordDTO accountRecordDto) {
        Optional<Account> accountO = accountRepository.findById(id);
        if (accountO.isEmpty()) {
            return null;
        }
        var account = accountO.get();
        BeanUtils.copyProperties(accountRecordDto, account);
        return accountRepository.save(account);
    }

    @Override
    public void setPaidStatus(Long id, boolean paidStatus) {
        Optional<Account> optionalAccount = accountRepository.findById(id);
        if (optionalAccount.isPresent()) {
            Account account = optionalAccount.get();
            account.setPaid(paidStatus);
            accountRepository.save(account);
        } else {
            throw new IllegalArgumentException("Conta não encontrada para o id: " + id);
        }
    }
}