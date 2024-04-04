package com.uece.contasapagar.controller;

import com.uece.contasapagar.dto.AccountRecordDTO;
import com.uece.contasapagar.entity.Account;
import com.uece.contasapagar.service.AccountService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {
    @Autowired
    private AccountService accountService;

    @GetMapping
    public ResponseEntity<List<Account>> getAllAccounts() {
        List<Account> accountsList = accountService.getAllAccounts();
        return ResponseEntity.status(HttpStatus.OK).body(accountsList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneAccount(@PathVariable(value = "id") Long id) {
        Account account = accountService.getAccountById(id);
        if (account == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Conta não encontrada.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(account);
    }

    @PostMapping
    public ResponseEntity<Account> saveAccount(@RequestBody @Valid AccountRecordDTO accountRecordDto) {
        Account savedAccount = accountService.saveAccount(accountRecordDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedAccount);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteAccount(@PathVariable(value = "id") Long id) {
        accountService.deleteAccount(id);
        return ResponseEntity.status(HttpStatus.OK).body("Conta deletada com sucesso.");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateAccount(@PathVariable(value = "id") Long id,
                                                @RequestBody @Valid AccountRecordDTO accountRecordDto) {
        Account updatedAccount = accountService.updateAccount(id, accountRecordDto);
        if (updatedAccount == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Conta não encontrada.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(updatedAccount);
    }

    @GetMapping("/paid")
    public ResponseEntity<List<Account>> getAllPaidAccounts() {
        List<Account> accountsList = accountService.getAllPaidAccounts();
        return ResponseEntity.status(HttpStatus.OK).body(accountsList);
    }

    @GetMapping("/not-paid")
    public ResponseEntity<List<Account>> getAllNotPaidAccounts() {
        List<Account> accountsList = accountService.getAllNotPaidAccounts();
        return ResponseEntity.status(HttpStatus.OK).body(accountsList);
    }

    @PutMapping("/{id}/paid")
    public ResponseEntity<Object> setPaidStatus(@PathVariable Long id, @RequestParam boolean paidStatus) {
        try {
            accountService.setPaidStatus(id, paidStatus);
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }
}