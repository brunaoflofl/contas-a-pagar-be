package com.uece.contasapagar.repository;

import com.uece.contasapagar.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
}