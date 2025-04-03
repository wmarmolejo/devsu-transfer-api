package com.devsu.transfer_api.controller.impl;

import com.devsu.transfer_api.controller.IAccountController;
import com.devsu.transfer_api.dto.request.AccountRequestDto;
import com.devsu.transfer_api.dto.response.AccountResponseDto;
import com.devsu.transfer_api.mapper.IAccountMapper;
import com.devsu.transfer_api.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api")
public class AccountController implements IAccountController {

    @Autowired
    private IAccountService accountService;
    @Autowired
    private IAccountMapper accountMapper;

    @Override
    public ResponseEntity<Mono<AccountResponseDto>> save(@RequestBody AccountRequestDto accountRequestDto) {

        return ResponseEntity.status(HttpStatus.CREATED).body(accountService
                .createAccount(accountMapper.toEntityFromCreate(accountRequestDto)));
    }

    @Override
    public ResponseEntity<Mono<AccountResponseDto>> getAccountNumberAndType(
            @RequestParam(value = "numberAccount", required = false) String numberAccount,
            @RequestParam(value = "typeAccount", required = false) String typeAccount){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(accountService.getAccountByNumberAccountAndTypeAccount(numberAccount,typeAccount));
    }

    @Override
    public ResponseEntity<Flux<AccountResponseDto>> getAccounts(){
        return ResponseEntity.status(HttpStatus.OK).body(accountService.getAllAccount());
    }

    @Override
    public ResponseEntity<?> disableAccountNumberAndType(
            @RequestParam(value = "numberAccount", required = false) String numberAccount,
            @RequestParam(value = "typeAccount", required = false) String typeAccount){
        return ResponseEntity.status(HttpStatus.OK).body(accountService.disableAccount(numberAccount,typeAccount));
    }


}
