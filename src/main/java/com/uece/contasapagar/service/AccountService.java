package com.uece.contasapagar.service;

import com.uece.contasapagar.dto.AccountRecordDTO;
import com.uece.contasapagar.entity.Account;

import java.util.List;

public interface AccountService {
    List<Account> getAllAccounts();

    List<Account> getAllPaidAccounts();

    List<Account> getAllNotPaidAccounts();

    Account getAccountById(Long id);

    Account saveAccount(AccountRecordDTO accountRecordDto);

    void deleteAccount(Long id);

    void setPaidStatus(Long id, boolean paidStatus);

    Account updateAccount(Long id, AccountRecordDTO accountRecordDto);
}