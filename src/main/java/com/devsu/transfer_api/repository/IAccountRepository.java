package com.devsu.transfer_api.repository;


import com.devsu.transfer_api.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAccountRepository extends JpaRepository<Account, Long> {

    Account findByNumberAccountAndTypeAccount(String numberAccount, String type);
}
