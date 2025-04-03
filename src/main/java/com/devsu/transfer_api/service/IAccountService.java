package com.devsu.transfer_api.service;


import com.devsu.transfer_api.dto.response.AccountResponseDto;
import com.devsu.transfer_api.model.Account;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Optional;

public interface IAccountService {

    Mono<AccountResponseDto> createAccount(Account account);
    Mono<AccountResponseDto> getAccountByNumberAccountAndTypeAccount(String numberAccount, String typeAccount);
    Flux<AccountResponseDto> getAllAccount();
    String disableAccount(String numberAccount, String typeAccount);

    Optional<Account> findById(Long id);

    Optional<Account> update(Long id, Account account);
}
