package com.devsu.transfer_api.service;

import com.devsu.transfer_api.client.IClientService;
import com.devsu.transfer_api.dto.response.AccountResponseDto;
import com.devsu.transfer_api.dto.response.ClientResponseDto;
import com.devsu.transfer_api.mapper.IAccountMapper;
import com.devsu.transfer_api.model.Account;
import com.devsu.transfer_api.repository.IAccountRepository;
import com.devsu.transfer_api.service.impl.AccountService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AccountServiceTest {

    @Mock
    private IAccountRepository accountRepository;

    @Mock
    private IClientService clientService;

    @Mock
    private IAccountMapper accountMapper;

    @InjectMocks
    private AccountService accountService;

    private Account account;
    private AccountResponseDto accountResponseDto;
    private ClientResponseDto clientResponseDto;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        // Preparar datos mockeados
        account = new Account();
        account.setId(1L);
        account.setClientId(123L);
        account.setNumberAccount("123456");
        account.setTypeAccount("Checking");
        account.setStatus(true);
        account.setAvailableBalance(100.0);

        accountResponseDto = new AccountResponseDto();
        accountResponseDto.setClient("123");
        accountResponseDto.setNumberAccount(account.getNumberAccount());
        accountResponseDto.setTypeAccount(account.getTypeAccount());

        clientResponseDto = new ClientResponseDto();
        clientResponseDto.setId(1L);
        clientResponseDto.setName("John Doe");
        clientResponseDto.setAddress("Calle 123");
        clientResponseDto.setGender("M");
        clientResponseDto.setTelephone("123456789");
        clientResponseDto.setAge(30);
        clientResponseDto.setStatus(true);
        when(accountMapper.toResponseDto(account)).thenReturn(accountResponseDto);
    }

    @Test
    public void testCreateAccount_success() {
        when(clientService.obtenerClientePorId(account.getClientId()))
                .thenReturn(Mono.just(clientResponseDto));
        when(accountRepository.save(account)).thenReturn(account);
        Mono<AccountResponseDto> result = accountService.createAccount(account);
        StepVerifier.create(result)
                .expectNextMatches(response -> {
                    assertNotNull(response);
                    assertEquals(account.getNumberAccount(), response.getNumberAccount());
                    assertEquals(clientResponseDto.getName(), response.getClient());
                    return true;
                })
                .verifyComplete();
    }

    @Test
    public void testGetAccountByNumberAccountAndTypeAccount_found() {
        when(accountRepository.findByNumberAccountAndTypeAccount(account.getNumberAccount(), account.getTypeAccount()))
                .thenReturn(account);
        when(clientService.obtenerClientePorId(account.getClientId()))
                .thenReturn(Mono.just(clientResponseDto));
        Mono<AccountResponseDto> result = accountService.getAccountByNumberAccountAndTypeAccount(account.getNumberAccount(), account.getTypeAccount());

        // Verificación de que se obtiene la cuenta correctamente
        StepVerifier.create(result)
                .expectNextMatches(response -> {
                    assertNotNull(response);
                    assertEquals(account.getNumberAccount(), response.getNumberAccount());
                    assertEquals(clientResponseDto.getName(), response.getClient());
                    return true;
                })
                .verifyComplete();
    }


}