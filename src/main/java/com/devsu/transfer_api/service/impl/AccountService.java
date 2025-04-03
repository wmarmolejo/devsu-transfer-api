package com.devsu.transfer_api.service.impl;

import com.devsu.transfer_api.client.IClientService;
import com.devsu.transfer_api.dto.response.AccountResponseDto;
import com.devsu.transfer_api.enums.ErrorState;
import com.devsu.transfer_api.mapper.IAccountMapper;
import com.devsu.transfer_api.model.Account;
import com.devsu.transfer_api.repository.IAccountRepository;
import com.devsu.transfer_api.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.List;
import java.util.Optional;

@Service
public class AccountService implements IAccountService {
    @Autowired
    private IAccountRepository accountRepository;
    @Autowired
    private IClientService clientService;
    @Autowired
    private IAccountMapper accountMapper;

    @Transactional
    @Override
    public Mono<AccountResponseDto> createAccount(Account account) {
        return clientService.obtenerClientePorId(account.getClientId())
                .flatMap(cliente ->
                        Mono.fromCallable(() -> accountRepository.save(account))
                                .subscribeOn(Schedulers.boundedElastic())
                                .then(Mono.fromSupplier(() -> {
                                    AccountResponseDto accountResponseDto =accountMapper.toResponseDto(account);
                                    accountResponseDto.setClient(cliente.getName());
                                    return accountResponseDto;
                                }))
                );
    }

    @Transactional(readOnly = true)
    @Override
    public Mono<AccountResponseDto> getAccountByNumberAccountAndTypeAccount(String numberAccount, String typeAccount) {

        Account account = accountRepository.findByNumberAccountAndTypeAccount(numberAccount, typeAccount);
          if (account == null) {
              throw new RuntimeException(ErrorState.ACCOUNT_NOT_FOUND.getMessage());
        }
        AccountResponseDto accountResponseDto = accountMapper.toResponseDto(account);
        return clientService.obtenerClientePorId(Long.parseLong(accountResponseDto.getClient()))
                .doOnNext(clientResponseDto -> {
                    accountResponseDto.setClient(clientResponseDto.getName());
                })
                .then(Mono.just(accountResponseDto));
    }

    @Override
    public Flux<AccountResponseDto> getAllAccount() {
        List<Account> accounts = accountRepository.findAll();
        Flux<Account> accountFlux = Flux.fromIterable(accounts);

        return accountFlux.flatMap(account -> {
            AccountResponseDto accountResponseDto = accountMapper.toResponseDto(account);
            return clientService.obtenerClientePorId(Long.parseLong(accountResponseDto.getClient()))
                    .doOnNext(clientResponseDto -> {
                        accountResponseDto.setClient(clientResponseDto.getName());
                    })
                    .then(Mono.just(accountResponseDto));
        });
    }
    @Transactional
    @Override
    public String disableAccount(String numberAccount, String typeAccount) {
        Account  accoundFind = accountRepository.findByNumberAccountAndTypeAccount(numberAccount, typeAccount);

        if (accoundFind == null) {
            return ErrorState.ACCOUNT_NOT_FOUND.getMessage();
        }
        accoundFind.setStatus(false);
        accountRepository.save(accoundFind);
        return ErrorState.ACCOUNT_STATUS_CHANGE.getMessage();
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Account> findById(Long id) {
        Optional<Account> Account = accountRepository.findById(id);
        Account.orElseThrow(() -> new RuntimeException(ErrorState.ACCOUNT_NOT_FOUND.getMessage()));
        return Account;
    }
    @Transactional
    @Override
    public Optional<Account> update(Long id, Account account) {
        Account accountDB = findById(id).get();
        accountDB.setStatus(account.getStatus());
        accountDB.setAvailableBalance(account.getAvailableBalance());
        return Optional.of(accountRepository.save(accountDB));
    }


}
